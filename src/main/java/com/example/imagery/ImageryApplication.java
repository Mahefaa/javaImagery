package com.example.imagery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.imagery","Controller"})
public class ImageryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageryApplication.class, args);
    }

}
