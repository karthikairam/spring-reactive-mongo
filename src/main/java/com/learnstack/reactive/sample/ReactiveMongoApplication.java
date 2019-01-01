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

    @Bean
    CommandLineRunner employees(EmployeeRepo employeeRepo) {
        return args -> {
            employeeRepo
                    .deleteAll()
                    .subscribe(null, null, () -> {
                        Stream.of(new Employee(UUID.randomUUID().toString(), "Karthik", 20000.00),
                                new Employee(UUID.randomUUID().toString(), "Selvan", 30000.00),
                                new Employee(UUID.randomUUID().toString(), "Anirv", 50000.00),
                                new Employee(UUID.randomUUID().toString(), "Ram", 100000.00)
                        ).forEach(employee -> {
                            employeeRepo
                                    .save(employee)
                                    .subscribe(System.out::println);
                        });
                    });
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoApplication.class, args);
    }

}

