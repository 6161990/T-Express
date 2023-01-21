package com.yoon.boardingExpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class BoardingExpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardingExpressApplication.class, args);
    }
}
