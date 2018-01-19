package be.inventj.notemanager.api.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Created by j.peeters on 05/12/2017.
 */
@Component
public class AuthorHandler {

    @Autowired
    private AuthorRepository repository;

    public Mono<ServerResponse> listAll(ServerRequest request) {
        // ideally the repository should also give the results in a non-blocking way
        // but since dynamodb doesn't support true async yet we convert here to a stream and create a flux
        Flux<Author> authors = Flux.fromStream(repository.findAll().stream());
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(authors, Author.class);
    }

    public Mono<ServerResponse> add(Author author) {
        Author newAuthor = repository.save(author);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(Mono.just(newAuthor.getId()), String.class);
    }

    public Mono<ServerResponse> update(Author author) {

        Optional<Author> current = repository.findById(author.getId());
        if (current.isPresent()) {
            current.get().setName(author.getName());
            current.get().setEmail(author.getEmail());
        }
        Author result = repository.save(current.get());
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(Mono.just(result.getId()), String.class);
    }

    public Mono<ServerResponse> delete(Author author) {
        repository.deleteById(author.getId());
        return ServerResponse.ok()
                             .contentType(APPLICATION_JSON)
                             .body(Mono.just("author " + author.getId() + " deleted"), String.class);
    }
}
