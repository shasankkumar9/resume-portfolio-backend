package com.shasank.resumeportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ResumePortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumePortfolioApplication.class, args);
    }

}
