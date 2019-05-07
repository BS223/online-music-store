package com.oop.onlinemusicstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OnlinemusicstoreApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OnlinemusicstoreApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(OnlinemusicstoreApplication.class, args);
    }

}
