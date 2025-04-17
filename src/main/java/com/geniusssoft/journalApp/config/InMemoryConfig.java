package com.geniusssoft.journalApp.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * In-memory MongoDB configuration for testing
 */
@Configuration
@Profile("test")
@EnableMongoRepositories(basePackages = "com.geniusssoft.journalApp.repositorys")
public class InMemoryConfig {

    private static final String MONGODB_CONNECTION_STRING = "mongodb://localhost:27017/test";

    @Bean
    @Primary
    public MongoClient mongoClient() {
        // Mock MongoClient that just returns an empty connection that won't actually be used
        return MongoClients.create(MONGODB_CONNECTION_STRING);
    }

    @Bean
    @Primary
    public MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoClient(), "test");
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }
} 