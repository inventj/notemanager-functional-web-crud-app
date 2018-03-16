package be.inventj.notemanager.api.health;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * Created by j.peeters on 05/12/2017.
 */
@Component
public class VersionHandler {

    public static final String VERSION = "author-api-v1.0";

    public Mono<ServerResponse> getVersion(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(VERSION));
    }
}
