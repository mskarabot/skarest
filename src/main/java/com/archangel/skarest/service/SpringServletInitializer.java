package com.archangel.skarest.service;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class SpringServletInitializer extends org.springframework.boot.web.support.SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootService.class);
    }

}
