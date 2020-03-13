package ch.andreevi.examples.spring.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class WebRouter {

    private final MediaType json = MediaType.APPLICATION_JSON;

    @Bean
    public RouterFunction<ServerResponse> personEndpoint (PersonHandler handler){
    	return RouterFunctions
                .route(POST("/person").and(accept(json)), handler::createPerson)
                .andRoute(GET("/person/{id}").and(accept(json)), handler::findById)
                .andRoute(DELETE("/person/{id}").and(accept(json)), handler::remove)
                .andRoute(PUT("/person").and(accept(json)), handler::update);
    }
}