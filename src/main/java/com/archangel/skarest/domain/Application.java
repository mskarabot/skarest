package com.archangel.skarest.domain;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.KeyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Datastore cloudDatastoreService() {
        return DatastoreOptions.defaultInstance().service();
    }

    @Bean
    public KeyFactory cloudKeyFactory() {
        return cloudDatastoreService().newKeyFactory().kind("CarRefuel");
    }

}


