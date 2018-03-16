package be.inventj.notemanager.api;

import be.inventj.notemanager.api.author.Author;
import be.inventj.notemanager.api.author.AuthorRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.UUID;

/**
 * Created by j.peeters on 14/03/2018.
 */
@Profile("local-mongo")
@Configuration
@EnableReactiveMongoRepositories
public class LocalReactiveMongoDbConfig extends AbstractReactiveMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "notes-api-db";
    }

    @Override
    @Bean
    @DependsOn("embeddedMongoServer")
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(String.format("mongodb://localhost"));
    }

    @Bean
    CommandLineRunner initData(AuthorRepository authorRepository) {
        return (p) -> {
            authorRepository.save(new Author(UUID.randomUUID().toString(),
                                             UUID.randomUUID().toString() + "@designisdead.com")).block();
        };
    }
}
