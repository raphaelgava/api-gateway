package com.api_gateway.configurations;

import com.api_gateway.filters.LoggingFilter;
import com.api_gateway.filters.PrePostLoggingFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.function.Function;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;

@Configuration
public class ApiGatewayConfiguration {
//    private final PrePostLoggingFilter prePostLoggingFilter;
//
//    public ApiGatewayConfiguration(PrePostLoggingFilter prePostLoggingFilter) {
//        this.prePostLoggingFilter = prePostLoggingFilter;
//    }
//
//    //Comentado aqui para pegar a configuração do application.yml
//    @Bean
//    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
//        Function<PredicateSpec, Buildable<Route>> function =
//                p -> p.path("/get")
//                                    .filters(f-> f.addRequestHeader("Hello", "World")
//                                                                    .addRequestParameter("Hello", "World"))
//                                    .uri("http://httpbin.org:80"); //O httpbin é uma ferramenta poderosa para testar e entender como funcionam as requisições HTTP.
//
//        return builder
//                .routes()
//                    .route(function)
//                    .route("cambio-service", p -> p.path("/cambio-service/**").uri("lb://ms-cambio-service"))
//                    .route("book-service", p -> p.path("/book-service/**").uri("lb://ms-book-service"))
//                    .route("openapi", p -> p.path("/v3/api-docs/**")
//                        .filters(f -> f
//                                .filter(prePostLoggingFilter)
//                                .rewritePath("/v3/api-docs/(?<path>.*)", "/${path}/v3/api-docs")
//                        )
//                        .uri("lb://api-gateway"))
//                .build();
//
//        //Esse código é o mesmo que a configuração no arquivo appliction.yml
//        //spring:
//        //  cloud:
//        //    gateway:
//        //      routes:
//        //      - id: ms-cambio-service
//        //          uri: lb://ms-cambio-service
//        //          predicates:
//        //            - Path=/ms-cambio-service/**
//    }
}
