package be.inventj.notemanager.api.author;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Created by j.peeters on 05/12/2017.
 */
@Configuration
public class AuthorRoute {

    @Bean
    public static RouterFunction<ServerResponse> listAll(AuthorHandler handler) {
        return route(GET("/author/list"), handler::listAll);
    }

    @Bean
    public static RouterFunction<ServerResponse> create(AuthorHandler handler) {
        return route(POST("/author/create"), request ->
                request.bodyToMono(Author.class).doOnNext(handler::add).then(ok().build()));
    }

    @Bean
    public static RouterFunction<ServerResponse> update(AuthorHandler handler) {
        return route(POST("/author/update"), request ->
                request.bodyToMono(Author.class).doOnNext(handler::update).then(ok().build()));
    }

    @Bean
    public static RouterFunction<ServerResponse> delete(AuthorHandler handler) {
        return route(POST("/author/delete"), request ->
                request.bodyToMono(Author.class).doOnNext(handler::delete).then(ok().build()));
    }
}
