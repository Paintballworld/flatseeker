package com.yakzhanov.flatseeker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlatSeekerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlatSeekerApplication.class, args);
    }

}
