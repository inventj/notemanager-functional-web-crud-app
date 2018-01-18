package be.inventj.notemanager.api.author;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Created by j.peeters on 05/12/2017.
 */
@EnableScan
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Flux<Author> getAuthors();

    Mono<Author> addAuthor(Author author);
}
