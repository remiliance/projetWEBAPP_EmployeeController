package com.webetapi.webapp;

import com.webetapi.webapp.repository.EmployeeProxy;
import com.webetapi.webapp.service.MovieRatingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

    @Autowired
    MovieRatingServiceImp movieRatingServiceImp;

    @Autowired
    EmployeeProxy employeeProxy;

    @Override
    public Health health() {

        if (movieRatingServiceImp.getMovieRating("Rambo").isEmpty()) {
            return Health.down().withDetail("Cause", "Service OMDb API is not available").build();
        }
/*
        if (employeeProxy.getEmployees().isEmpty()) {
            return Health.down().withDetail("Cause", "Proxy service not available - API").build();
        }
*/
        return Health.up().build();
    }



}