package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Config {

    // Temporary solution.
    // Should be replaced with correct REST communication solution
    // after theory learning
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
