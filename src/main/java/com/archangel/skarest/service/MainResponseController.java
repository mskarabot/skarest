package com.archangel.skarest.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainResponseController {

    @GetMapping("/test")
    public String indexResponse() {
        return "Ska-rest-service, springboot-appengine-standard, running...";
    }

}