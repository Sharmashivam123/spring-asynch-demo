package com.ss.spring_asynch_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ConfigurationPropertiesScan
public class SpringAsynchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAsynchDemoApplication.class, args);
    }

}
