package com.RevisaAi.JavaProjects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.RevisaAi.JavaProjects"})
public class JavaProjectsApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaProjectsApplication.class, args);
    }
}