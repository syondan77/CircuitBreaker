package com.application.circuitBreaker.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CbService {
    private final RestTemplate restTemplate;

    public CbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Object> getUniversities() throws Exception {
        Object[] universities = null;
        try {
        	universities = restTemplate.getForObject("http://universities.hipolabs.com/search?country=United+States", Object[].class);
        } catch (Exception e) {
            throw new Exception("Failed to fetch countries from the API");
        }
        return Arrays.stream(universities).toList().subList(1, 10);
    }
}
