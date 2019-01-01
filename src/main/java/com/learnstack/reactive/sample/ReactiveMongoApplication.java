package com.learnstack.reactive.sample;

import com.learnstack.reactive.sample.domainobject.Employee;
import com.learnstack.reactive.sample.repo.EmployeeRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoApplication.class, args);
    }

}

