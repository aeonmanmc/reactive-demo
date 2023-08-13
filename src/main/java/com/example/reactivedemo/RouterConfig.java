package com.example.reactivedemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(SampleHandler handler){
        return RouterFunctions.route()
                .GET("/functional/flux", handler::returnFlux)
                .GET("/functional/mono", handler::returnMono)
                .build();
    }
}
