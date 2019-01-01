package com.learnstack.reactive.sample.routefunction;

import com.learnstack.reactive.sample.routefunction.handlers.RouterHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Configuration
public class EmployeeRoute {

    @Bean
    RouterFunction<?> routerFunction(RouterHandlers routerHandlers) {
        return RouterFunctions.nest(path("/rest/route/employees"),
                RouterFunctions.route(GET("/"), routerHandlers::getAll)
                .andRoute(GET("/{id}"), routerHandlers::getByID)
                .andRoute(GET("/{id}/events"),routerHandlers::getEvents));
    }
}
