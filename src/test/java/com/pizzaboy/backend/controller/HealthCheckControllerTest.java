package com.pizzaboy.backend.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class HealthCheckControllerTest {
    HealthCheckController healthCheckController = new HealthCheckController();
    @Test
    public void healthCheckTest() {
        Assertions.assertEquals(healthCheckController.healthCheck(), "Healthy");
    }
}
