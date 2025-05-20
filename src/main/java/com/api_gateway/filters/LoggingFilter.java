package com.api_gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    // Permite trabalhar com tudo o que está passando pelo api-gateway, no caso aqui apenas logando.
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.error("Original request path -> {}", exchange.getRequest().getPath());
        return chain.filter(exchange);

//        String originalUri = exchange.getRequest().getURI().toString();
//        logger.error("Request URI before filter: {}", originalUri);
//        return chain.filter(exchange).doOnRequest(aVoid -> {
//            String newUri = exchange.getRequest().getURI().toString();
//            logger.error("Request URI after filters: {}", newUri);
//        });
    }
}
