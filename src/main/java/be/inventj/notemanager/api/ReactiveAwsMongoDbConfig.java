package be.inventj.notemanager.api;

import be.inventj.notemanager.api.author.Author;
import be.inventj.notemanager.api.author.AuthorRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.UUID;

/**
 * Created by j.peeters on 14/03/2018.
 */
@Profile("aws-mongo")
@Configuration
@EnableReactiveMongoRepositories
public class ReactiveAwsMongoDbConfig extends AbstractReactiveMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "notes-api-db";
    }

    @Override
    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(String.format("mongodb://ec2-18-197-101-15.eu-central-1.compute.amazonaws.com:27017"));
    }

    @Bean
    CommandLineRunner initData(AuthorRepository authorRepository) {
        return (p) -> {
            authorRepository.save(new Author(UUID.randomUUID().toString(),
                                             UUID.randomUUID().toString() + "@designisdead.com")).block();
        };
    }
}
