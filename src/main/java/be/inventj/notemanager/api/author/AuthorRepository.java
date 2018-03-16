package be.inventj.notemanager.api.author;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


/**
 * Created by j.peeters on 05/12/2017.
 */
@EnableScan
public interface AuthorRepository extends ReactiveCrudRepository<Author, String> {
}
