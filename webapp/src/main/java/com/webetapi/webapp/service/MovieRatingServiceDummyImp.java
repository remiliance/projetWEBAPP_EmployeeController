package com.webetapi.webapp.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(name = "app.environment", havingValue = "dev")
@Service
public class MovieRatingServiceDummyImp implements MovieRatingService {

    @Override
    public String getMovieRating(String title) {
        return "19.99";
    }


}
