package com.lacombe.kata.coffeeMachine;

public class CoffeeInstructionException extends RuntimeException{
    private static final String UNKNOWN_DRINK_TYPE = "Unknown drink type";
    private static final String SUGAR_QUANTITY_IS_NOT_NUMERIC = "Sugar quantity must be numeric";

    private CoffeeInstructionException(String message) {
        super(message);
    }

    public static CoffeeInstructionException invalidDrinkType() {
        return new CoffeeInstructionException(UNKNOWN_DRINK_TYPE);
    }

    public static CoffeeInstructionException sugarQuantityMustBeNumeric() {
        return new CoffeeInstructionException(SUGAR_QUANTITY_IS_NOT_NUMERIC);
    }

}
