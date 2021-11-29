package com.example.demo.router;

import com.example.demo.handler.Handler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.RouteMatcher;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.*;

@Configuration
@EnableWebFlux
public class Route implements WebFluxConfigurer {
    @Bean
    public RouterFunction<ServerResponse> demo(Handler handler){
        return RouterFunctions.route(RequestPredicates.GET("/get"),handler::get)
                .andRoute(RequestPredicates.POST("/save"),handler::save)
                .andRoute(RequestPredicates.DELETE("/delete/{id}"),handler::delete)
                .andRoute(RequestPredicates.PUT("/update/{id}"),handler::update);
    }
}
