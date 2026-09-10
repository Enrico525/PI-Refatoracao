package com.mycompany.pi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PiWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                PiWebApplication.class,
                args
        );
    }
}