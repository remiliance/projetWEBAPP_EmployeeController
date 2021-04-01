package com.webetapi.webapp.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webetapi.webapp.CustomProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@ConditionalOnProperty(name = "app.environment", havingValue = "prod")
@Service
public class MovieRatingServiceImp implements MovieRatingService {


    @Autowired
    private CustomProperties props;


    @Override
    public String getMovieRating(String title) {

        String apiUrl = props.getApiUrlRating();
        Logger logger = LoggerFactory.getLogger(MovieRatingServiceImp.class);

        try {
            logger.debug("Calling omdbapi with url {} and title {}", apiUrl, title);
            RestTemplate template = new RestTemplate();
            ResponseEntity<ObjectNode> response =
                    template.getForEntity(apiUrl + title, ObjectNode.class);

            ObjectNode jsonObject = response.getBody();

            logger.error("rating found for {} happened!", title);
            return jsonObject.path("imdbRating").asText();
        } catch (Exception e) {
            logger.error("ERROR! Exception happened!", e);
            return null;
        }
    }

}
