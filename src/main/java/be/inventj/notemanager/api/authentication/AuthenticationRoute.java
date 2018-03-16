package be.inventj.notemanager.api.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AuthenticationRoute {
    @Bean
    public static RouterFunction<ServerResponse> verify(AuthenticationHandler handler) {
        return route(POST("/auth/token"), handler::verify);
    }

}
