package com.example.demo.handler;

import com.example.demo.model.DemoString;
import com.example.demo.repository.DemoRepository;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class Handler {
    @Autowired
    DemoService demoService;

    public Mono<ServerResponse> get(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(demoService.getString(),DemoString.class);
    }
    public Mono<ServerResponse> save(ServerRequest request){
        return request.bodyToMono(DemoString.class).flatMap(demoString->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                        body(demoService.saveOne(demoString),DemoString.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        Mono<DemoString> deleted = demoService.deleteOne(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                body(deleted,DemoString.class);
    }

    public Mono<ServerResponse> update(ServerRequest request){
        return request.bodyToMono(DemoString.class).flatMap(demoString->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                body(demoService.updateOne(demoString,request.pathVariable("id")),DemoString.class));
    }

}
