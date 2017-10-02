package com.archangel.skarest.domain;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
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
        // this project id has to be here, otherwise it sets the no_project_id
        return DatastoreOptions.newBuilder().setProjectId("ska-rest").build().getService();
    }

}


