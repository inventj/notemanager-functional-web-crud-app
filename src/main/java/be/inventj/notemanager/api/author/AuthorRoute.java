package be.inventj.notemanager.api.author;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by j.peeters on 05/12/2017.
 */
@Configuration
public class AuthorRoute {

    @Bean
    public static RouterFunction<ServerResponse> getById(AuthorHandler handler) {
        return route(GET("/author/{id}").and(contentType(APPLICATION_JSON)), handler::getById);
    }

    @Bean
    public static RouterFunction<ServerResponse> listAll(AuthorHandler handler) {
        return route(GET("/author/list"), handler::list);
    }

    @Bean
    public static RouterFunction<ServerResponse> create(AuthorHandler handler) {
        return route(POST("/author").and(contentType(APPLICATION_JSON)), handler::add);
    }

    @Bean
    public static RouterFunction<ServerResponse> delete(AuthorHandler handler) {
        return route(DELETE("/author/{id}").and(contentType(APPLICATION_JSON)), handler::remove);
    }

    @Bean
    public static RouterFunction<ServerResponse> update(AuthorHandler handler) {
        return route(PUT("/author").and(contentType(APPLICATION_JSON)), handler::update);
    }
}
