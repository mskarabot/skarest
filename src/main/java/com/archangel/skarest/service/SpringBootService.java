package com.archangel.skarest.service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "com.archangel.skarest.domain.carfuel",
        "com.archangel.skarest.service"})
public class SpringBootService {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootService.class, args);
    }

    @Bean
    public DatastoreService cloudDatastoreService() {
        return DatastoreServiceFactory.getDatastoreService();
    }

}


