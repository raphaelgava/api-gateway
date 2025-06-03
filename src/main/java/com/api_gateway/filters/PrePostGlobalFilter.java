package com.api_gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class PrePostGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrePostGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI originalUri = exchange.getRequest().getURI();
        String path = exchange.getRequest().getURI().getPath();
        LOGGER.info("Iniciando requisição para: {}", path);
        long startTime = System.currentTimeMillis();

//        if (!path.endsWith("/")){
//            LOGGER.info("Adicionando / ao final");
//            String newPath = path + "/";
//            URI newUri = UriComponentsBuilder.fromUri(originalUri)
//                    .replacePath(newPath)
//                    .build()
//                    .toUri();
//
//            var mutatedRequest = exchange.getRequest().mutate().uri(newUri).build();
//            var mutatedExchange = exchange.mutate().request(mutatedRequest).build();
//            return chain.filter(mutatedExchange);
//        }

        return chain.filter(exchange).doOnSuccess(aVoid -> {
            long duration = System.currentTimeMillis() - startTime;
            LOGGER.info("Requisição para {} completada em {}ms com status {}",
                    path, duration, exchange.getResponse().getStatusCode());
        });
    }

    @Override
    public int getOrder() {
        // Prioridade alta (quanto menor o número, maior a prioridade)
        return -1;
    }
}
