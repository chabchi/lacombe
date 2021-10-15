package com.lacombe.kata.coffeeMachine;


public class Drink {

    private static final String EMPTY_BLANK = "";
    private static final String ZERO_CHAR = "0";
    private static final String INSTRUCTION_SEPARATOR = ":";

    private DrinkType type;
    private String content;
    private boolean withStick;

    public Drink(String drinkType, String content) {
        validateInstruction(drinkType, content);
        createDrink(drinkType, content);
    }


    public DrinkType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public boolean isWithStick() {
        return withStick;
    }

    private void createDrink(String drinkType, String content) {
        this.type = DrinkType.get(drinkType);
        this.content = content;
        this.withStick = !String.valueOf(content).equals(ZERO_CHAR) ? true : false;
    }

    private String messageInstruction() {
        String type = this.getType().name();
        String content = this.getContent() == null ? EMPTY_BLANK : this.getContent();
        return type + INSTRUCTION_SEPARATOR + content;
    }

    private String coffeeInstruction() {
        String type = this.getType().name();
        String sugarQuantity = Integer.parseInt(this.getContent()) == 0 ? EMPTY_BLANK : this.getContent();
        String stick = this.isWithStick() ? ZERO_CHAR : EMPTY_BLANK;
        return type +INSTRUCTION_SEPARATOR + sugarQuantity + INSTRUCTION_SEPARATOR + stick;
    }

    public String makeCoffee(String drinkType, String content) {
        Drink drink = new Drink(drinkType, content);
        switch (drink.getType()) {
            case M:
                return drink.messageInstruction();
            default:
                return drink.coffeeInstruction();
        }
    }

    public static boolean isNumeric(String value) {
        if (value == null) {
            return false;
        }
        try {
            int number = Integer.valueOf(value);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void validateInstruction(String drinkType, String content) {
            DrinkType type = DrinkType.get(drinkType);
            if(type == null) {
                throw CoffeeInstructionException.invalidDrinkType();
            }
            if(!DrinkType.M.equals(type) && !isNumeric(content)) {
                throw CoffeeInstructionException.sugarQuantityMustBeNumeric();
            }

    }
}
