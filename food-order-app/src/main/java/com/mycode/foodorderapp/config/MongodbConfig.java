package com.mycode.foodorderapp.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableMongoRepositories(basePackages = "com.mycode.foodorderapp.repository")
public class MongodbConfig {

    @Value("${mongodb.url}")
    private String mongoDbUrl;

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoDbUrl);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToConnectionPoolSettings(builder -> builder
                        .maxConnectionIdleTime(60000, TimeUnit.MILLISECONDS)
                        .maxConnectionLifeTime(120000, TimeUnit.MILLISECONDS)
                        .minSize(1)
                        .maxSize(10)
                        .build())
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoDbUrl);
    }

}
