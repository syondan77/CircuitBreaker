package com.application.circuitBreaker.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.circuitBreaker.Service.CbService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CbController {


    private final CbService cbService;

    public CbController(CbService cbService) {
        this.cbService = cbService;
    }

    @GetMapping("/universities")
    @CircuitBreaker(name = "CircuitBreaker", fallbackMethod =  "getUniversities")
    public List<Object> getUniversities() throws Exception {
        return cbService.getUniversities();
    }

    public List<Object> getUniversities(Throwable throwable) {
        List<Object> universities = new ArrayList<>();
        System.out.println("--"+throwable.getMessage());
        universities.add("Universities service unavailable!");
        return universities;
    }
}
