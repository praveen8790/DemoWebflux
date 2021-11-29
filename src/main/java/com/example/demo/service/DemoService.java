package com.example.demo.service;

import com.example.demo.model.DemoString;
import com.example.demo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@org.springframework.stereotype.Service
public class DemoService {
    @Autowired
    DemoRepository demoRepository;
    public Flux<DemoString> getString(){
        return demoRepository.findAll();
    }
    public Mono<DemoString> saveOne(DemoString demoString){
        return demoRepository.insert(demoString);
    }
    public Mono<DemoString> deleteOne(String id){
        Mono<DemoString> obj = demoRepository.findById(id);
        if(Objects.isNull(obj)) {
            return Mono.empty();
        }
        return obj.flatMap(o->demoRepository.deleteById(id).thenReturn(o));
    }
    public Mono<DemoString> updateOne(DemoString demoString,String id){
        demoString.setId(id);
        return demoRepository.findById(demoString.getId()).switchIfEmpty(Mono.empty())
                .then(demoRepository.save(demoString));
    }
}
