package com.pizzaboy.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping(value="/health")
    String healthCheck() {
        return "Healthy";
    }
}
