package com.lacombe.kata.coffeeMachine;

import java.util.Arrays;

public enum DrinkType {
    T(0.4), H(0.5), C(0.6), M(0);

    private final double price;

    DrinkType(double price) {
        this.price = price;
    }

    public static DrinkType get(String value) {
        return Arrays.stream(values()).filter(type -> type.name().equalsIgnoreCase(value)).findFirst().orElse(null);
    }

    public double getPrice() {
        return price;
    }

}
