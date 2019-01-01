package com.emirates.reactive.Reactivemongo.resource;

import com.emirates.reactive.Reactivemongo.domainobject.Employee;
import com.emirates.reactive.Reactivemongo.domainobject.EmployeeEvent;
import com.emirates.reactive.Reactivemongo.repo.EmployeeRepo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Calendar;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/employees")
public class EmployeeService {

    private EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/")
    public Flux<Employee> getAll() {
        return employeeRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Employee> getEmployeeByID(@PathVariable("id") final String empId) {
        return employeeRepo
                .findById(empId);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEmployeeEvents(@PathVariable("id") final String empId) {
        return employeeRepo.findById(empId)
                .flatMapMany(employee -> {
                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
                    Flux<EmployeeEvent> employeeEventFlux =
                            Flux.fromStream(
                                    Stream.generate(() -> new EmployeeEvent(employee, Calendar.getInstance().getTime()))
                            );
                    return Flux.zip(interval, employeeEventFlux)
                            .map(Tuple2::getT2);
                });
    }
}
