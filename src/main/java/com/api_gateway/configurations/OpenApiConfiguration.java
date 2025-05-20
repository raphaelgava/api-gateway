package com.api_gateway.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@EnableWebFlux
//@OpenAPIDefinition(
//        info = @Info(
//                title = "API Gateway Documentation",
//                version = "v1",
//                description = "Documentation for all microservices"
//        )
//)
public class OpenApiConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(OpenApiConfiguration.class);

//    //http://localhost:8765/swagger-ui.html
//    @Bean
//    @Lazy(false) //Para carregar na inicialização e não aguardar
//    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters config, RouteDefinitionLocator locator){
//        var definition = locator.getRouteDefinitions().collectList().block();
//
//        definition
//                .stream()
//                .filter(routeDefinition -> routeDefinition.getId()
//                        .matches(".*-service"))
//                .forEach(routeDefinition -> {
//                    String name = routeDefinition.getId();
//                    config.addGroup(name);
//                    GroupedOpenApi.builder()
//                            .pathsToMatch("/" + name + "/**")
//                            .group(name)
//                            .build();
//                });
//        return new ArrayList<>();
//    }

    //http://localhost:8765/swagger-ui/index.html
    //http://localhost:8765/swagger-ui.html
    @Bean
    @Lazy(false) //Para carregar na inicialização e não aguardar
    public List<GroupedOpenApi> apis(RouteDefinitionLocator locator){ //RouteDefinitionLocator só serve se estiver declarado no applicatoin.yml
        logger.info("OpenApiConfiguration from ApiGateway is initializing");
        logger.info(locator.toString());

        List<GroupedOpenApi> groups = new ArrayList<>();
        var definitions = locator.getRouteDefinitions().collectList().block();
        logger.info(definitions.toString());

        definitions.stream()
                .filter(rd -> rd.getId()
                        .matches(".*-service"))
                            .forEach(rd -> {
                                groups.add(GroupedOpenApi.builder()
                                        .pathsToMatch("/" + rd.getId() + "/**")
                                        .group(rd.getId()) //Gerando o combobox com as opções dos serviços que estão rodando
                                        .build());
                            });

        logger.info(groups.stream().toArray().toString());

        return groups;
    }

//    @Bean
//    public OpenAPI customOpenAPI(){
//        logger.info("OpenApiConfiguration from Api Gateway is initializing");
//        return new OpenAPI()
//                .components(new Components())
//                .info(
//                        new io.swagger.v3.oas.models.info.Info()
//                                .title("Api Gateway")
//                                .version("v1")
//                                .license(
//                                        new License() // Aqui é conforme a organização da empresa
//                                                .name("Apache 2.0")
//                                                .url("http://springdoc.org")
//                                )
//                );
//    }
}
