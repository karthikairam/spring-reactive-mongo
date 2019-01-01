package com.learnstack.reactive.sample.routefunction.handlers;

import com.learnstack.reactive.sample.domainobject.Employee;
import com.learnstack.reactive.sample.domainobject.EmployeeEvent;
import com.learnstack.reactive.sample.repo.EmployeeRepo;
import com.mongodb.connection.Server;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Calendar;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class RouterHandlers {

    private EmployeeRepo employeeRepo;

    public RouterHandlers(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(
                        employeeRepo.findAll(), Employee.class
                );
    }

    public Mono<ServerResponse> getByID(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(
                        employeeRepo.findById(serverRequest.pathVariable("id")), Employee.class
                );
    }

    public Mono<ServerResponse> getEvents(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(TEXT_EVENT_STREAM)
                .body(employeeRepo.findById(serverRequest.pathVariable("id")).flatMapMany(
                        employee -> {
                            Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));

                            Flux<EmployeeEvent> employeeEventFlux =
                                    Flux.fromStream(
                                            Stream.generate(() -> new EmployeeEvent(employee, Calendar.getInstance().getTime()))
                                    );
                            return Flux.zip(interval, employeeEventFlux).map(Tuple2::getT2);
                        }
                ), EmployeeEvent.class);
    }
}
