package com.emirates.reactive.Reactivemongo.repo;

import com.emirates.reactive.Reactivemongo.domainobject.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepo extends ReactiveMongoRepository<Employee, String> {
}
