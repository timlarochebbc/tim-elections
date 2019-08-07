package com.tim.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.tim.model"} )
@EnableJpaRepositories("com.tim.model")
@ComponentScan(basePackages = {"com.tim", "com.tim.model"})
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}
