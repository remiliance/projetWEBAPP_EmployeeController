package com.webetapi.webapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import org.springframework.web.client.RestTemplate;

@Data
@Configuration
@ConfigurationProperties(prefix="com.openclassrooms.webapp")
public class CustomProperties {

    private String apiUrl;
    private String apiUrlRating;

    public String getApiUrl() {
        return apiUrl;
    }


    public String getApiUrlRating() {
        return apiUrlRating;
    }

}
