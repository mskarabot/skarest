package com.archangel.skarest.domain;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static final String clientID =
            "242906632695-rdi2g4j5uvjosfsqg0ghr3kvlhm2841s.apps.googleusercontent.com";

    public static final String clientSecret =
            "uiPEanB6ike0DXHRRN0jnObM";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DatastoreService cloudDatastoreService() {
        // this project id has to be here, otherwise it sets the no_project_id
        //return DatastoreOptions.newBuilder().setProjectId("ska-rest").build().getService();

        //return DatastoreOptions.getDefaultInstance().getService();

        return DatastoreServiceFactory.getDatastoreService();
    }

}


