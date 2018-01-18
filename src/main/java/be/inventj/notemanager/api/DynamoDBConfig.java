package be.inventj.notemanager.api;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by j.peeters on 17/01/2018.
 */
@Configuration
@EnableDynamoDBRepositories
        (basePackages = "be.inventj.notemanager.api")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB  = AmazonDynamoDBClientBuilder.standard()
                                          .withCredentials(getBasicAWSCredentials())
                                          .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                                                  amazonDynamoDBEndpoint,
                                                  ""))
                                          .build();

        return amazonDynamoDB;
    }

    @Bean
    public AWSStaticCredentialsProvider getBasicAWSCredentials() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                amazonAWSAccessKey, amazonAWSSecretKey));
    }

}