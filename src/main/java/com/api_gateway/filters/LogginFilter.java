package com.api_gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LogginFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(LogginFilter.class);

    // Permite trabalhar com tudo o que está passando pelo api-gateway, no caso aqui apenas logando.
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Original request path -> {}", exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
