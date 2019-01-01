package com.learnstack.reactive.sample.repo;

import com.learnstack.reactive.sample.domainobject.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepo extends ReactiveMongoRepository<Employee, String> {
}
