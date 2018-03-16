package be.inventj.notemanager.api.authentication;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface TokenRepository extends CrudRepository<Token, String> {
    List<Token> findAll();
}
