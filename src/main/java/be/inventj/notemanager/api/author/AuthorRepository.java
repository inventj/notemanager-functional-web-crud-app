package be.inventj.notemanager.api.author;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Created by j.peeters on 05/12/2017.
 */
@EnableScan
public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAll();
}
