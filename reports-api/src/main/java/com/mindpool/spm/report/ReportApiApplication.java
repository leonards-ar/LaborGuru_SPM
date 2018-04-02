package com.mindpool.spm.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = "com.mindpool.spm.report")
@SpringBootApplication
public class ReportApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportApiApplication.class, args);
    }
}
