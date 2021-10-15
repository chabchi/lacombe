package com.lacombe.kata.coffeeMachine;

import java.util.Arrays;

public enum DrinkType {
    T, H, C, M;

    public static DrinkType get(String value) {
        return Arrays.stream(values()).filter(type -> type.name().equalsIgnoreCase(value)).findFirst().orElse(null);
    }
}
