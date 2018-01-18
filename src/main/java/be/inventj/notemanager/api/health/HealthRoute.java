package be.inventj.notemanager.api.health;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by j.peeters on 05/12/2017.
 */
@Configuration
public class HealthRoute {
    @Bean
    public static RouterFunction<ServerResponse> routes(VersionHandler handler) {
        return route(GET("/author/version"), handler::getVersion);
    }
}
