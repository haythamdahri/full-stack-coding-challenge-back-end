package com.challenge.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Entry point for this project.
 *
 * @author HAYTHAM DAHRI
 */
@SpringBootApplication
@Configuration
public class BackendCodingChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendCodingChallengeApplication.class, args);
    }

}
