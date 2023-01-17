package com.pizzaboy.backend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {
    WAITING_FOR_CONFIRMATION("WAITING_FOR_CONFIRMATION"),
    PREPARING("PREPARING"),
    WAITING_FOR_PICKUP("WAITING_FOR_PICKUP"),
    ON_THE_WAY("ON_THE_WAY"),
    DELIVERED("DELIVERED"),
    REJECTED("REJECTED");

    private final String name;
}
