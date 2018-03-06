package com.tpaul.applicationtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties
public class ApplicationtrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationtrackerApplication.class, args);
    }
}
