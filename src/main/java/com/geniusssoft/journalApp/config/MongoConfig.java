package com.geniusssoft.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@Profile("!test") // Only activate when not in test profile
@EnableMongoRepositories(basePackages = "com.geniusssoft.journalApp.repositorys")
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        try {
            // Configure MongoDB client with longer timeouts
            ConnectionString connectionString = new ConnectionString(
                    "mongodb+srv://geniussingare:genius@cluster0.9ooay.mongodb.net/journaldb?retryWrites=true&w=majority&appName=Cluster0"
            );
            
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .applyToSslSettings(builder -> {
                        builder.enabled(true);
                        builder.invalidHostNameAllowed(true);
                    })
                    .applyToSocketSettings(builder -> {
                        builder.connectTimeout(5000, java.util.concurrent.TimeUnit.MILLISECONDS);
                        builder.readTimeout(5000, java.util.concurrent.TimeUnit.MILLISECONDS);
                    })
                    .build();
            
            return MongoClients.create(settings);
        } catch (MongoException e) {
            System.err.println("MongoDB connection error: " + e.getMessage());
            // Provide a fallback for development/testing
            return null;
        }
    }
    
    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient) {
        if (mongoClient == null) {
            // If MongoDB isn't available, this bean will be null
            // The application will fall back to TestConfig in this case
            return null;
        }
        return new SimpleMongoClientDatabaseFactory(mongoClient, "journaldb");
    }
    
    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
        if (mongoDatabaseFactory == null) {
            // If MongoDB isn't available, this bean will be null
            // The application will fall back to TestConfig in this case
            return null;
        }
        return new MongoTemplate(mongoDatabaseFactory);
    }
    
    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        if (dbFactory == null) {
            // If MongoDB isn't available, this bean will be null
            return null;
        }
        return new MongoTransactionManager(dbFactory);
    }
} 