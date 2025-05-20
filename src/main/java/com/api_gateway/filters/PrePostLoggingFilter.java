package com.api_gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
//public class PrePostLoggingFilter implements GlobalFilter, GatewayFilter {
//    private static final Logger logger = LoggerFactory.getLogger(PrePostLoggingFilter.class);
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest requestBeforeFilters = exchange.getRequest();
//        logger.info("URI antes do RewritePath e demais filtros: {}", requestBeforeFilters.getURI());
//        // Continuar a cadeia de filtros e após finalizar logar novamente
//        return chain.filter(exchange).doOnSuccess(aVoid -> {
//            ServerHttpRequest requestAfterFilters = exchange.getRequest();
//            logger.info("URI após aplicar RewritePath e demais filtros: {}", requestAfterFilters.getURI());
//        });
//    }
//}
public class PrePostLoggingFilter extends AbstractGatewayFilterFactory<PrePostLoggingFilter.Config> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrePostLoggingFilter.class);

    public PrePostLoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            // Pre-processing
            long startTime = System.currentTimeMillis();
            String path = exchange.getRequest().getURI().getPath();
            LOGGER.info("Request to {} started", path);

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // Post-processing
                        long endTime = System.currentTimeMillis();
                        LOGGER.info("Request to {} completed in {}ms with status {}",
                                path,
                                endTime - startTime,
                                exchange.getResponse().getStatusCode());
                        // Log the transformed path
                        LOGGER.info("Path was rewritten to: {}",
                                exchange.getRequest().getPath().value());
                    }));
        }, 1);
    }

    // Configuration class (required for AbstractGatewayFilterFactory)
    public static class Config {
        // You can add configuration properties here if needed
    }
}
