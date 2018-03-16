package be.inventj.notemanager.api.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * Created by j.peeters on 14/03/2018.
 */
@Component
public class AuthorHandler {
    @Autowired
    private AuthorRepository repository;

    public Mono<ServerResponse> list(ServerRequest request) {
        Flux<Author> authors = repository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(authors, Author.class);
    }

    public Mono<ServerResponse> add(ServerRequest request) {
        Mono<Author> author = request.bodyToMono(Author.class);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(repository.save(author.block()), Author.class);
    }

    public Mono<ServerResponse> remove(ServerRequest request) {
        String id = request.pathVariable("id");

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Author> authorMono = repository.findById(id);

        authorMono.then(repository.delete(authorMono.block())).block();
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Mono<Author> authorMono = request.bodyToMono(Author.class);
        return ServerResponse.ok()
                             .contentType(APPLICATION_JSON)
                             .body(repository.save(authorMono.block()), Author.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Author> authorMono = repository.findById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return authorMono
                .flatMap(author -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(author)))
                .switchIfEmpty(notFound);
    }
}
