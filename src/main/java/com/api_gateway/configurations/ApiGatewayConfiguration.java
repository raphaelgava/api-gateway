package com.api_gateway.configurations;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        Function<PredicateSpec, Buildable<Route>> function =
                p -> p.path("/get")
                                    .filters(f-> f.addRequestHeader("Hello", "World")
                                                                    .addRequestParameter("Hello", "World"))
                                    .uri("http://httpbin.org:80"); //O httpbin é uma ferramenta poderosa para testar e entender como funcionam as requisições HTTP.

        return builder
                .routes()
                    .route(function)
                    .route(p -> p.path("/cambio-service/**").uri("lb://ms-cambio-service"))
                    .route(p -> p.path("/book-service/**").uri("lb://ms-book-service"))
                .build();

        //Esse código é o mesmo que a configuração no arquivo appliction.yml
        //spring:
        //  cloud:
        //    gateway:
        //      routes:
        //        - id: ms-cambio-service
        //          uri: lb://ms-cambio-service
        //          predicates:
        //            - Path=/ms-cambio-service/**
    }
}
