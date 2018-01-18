package be.inventj.notemanager.api.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Created by j.peeters on 05/12/2017.
 */
@Component
public class AuthorHandler {

    @Autowired
    private AuthorRepository repository;

    public Mono<ServerResponse> listAll(ServerRequest request) {
        Flux<Author> authors = repository.getAuthors();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(authors, Author.class);
    }

    public void add(Author author) {
       repository.save(author);
    }
}
