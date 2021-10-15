package com.lacombe.kata.coffeeMachine;


import java.util.Optional;

public class Drink {

    private static final String EMPTY_BLANK = "";
    private static final String ZERO_CHAR = "0";
    private static final String INSTRUCTION_SEPARATOR = ":";
    private static final String YOU_ONLY_MISSES = "You only misses %s euro to get your drink!";
    private static final String HOT_DRINK_CHAR = "h";

    private DrinkType type;
    private String content;
    private boolean withStick;
    private boolean hot;

    public Drink(String drinkType, String content, boolean...  hot) {
        validateInstruction(drinkType, content);
        createDrink(drinkType, content, hot);
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

    private void createDrink(String drinkType, String content, boolean... hot) {
        this.type = DrinkType.get(drinkType);
        this.content = content;
        this.hot = hot.length > 0 ? hot[0] : false;
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
        String hotDrink = this.hot ? HOT_DRINK_CHAR : EMPTY_BLANK;
        return type + hotDrink  + INSTRUCTION_SEPARATOR + sugarQuantity + INSTRUCTION_SEPARATOR + stick;
    }

    public String makeCoffee(double money) {
        verifyEnoughMoney(money);
        switch (this.getType()) {
            case M:
                return this.messageInstruction();
            default:
                return this.coffeeInstruction();
        }
    }

    public void verifyEnoughMoney(double money){
        double priceDifference = calculatePriceDifference(this.getType() , money);
        if(priceDifference < 0) {
            this.type = DrinkType.M;
            this.content = String.format(YOU_ONLY_MISSES, -priceDifference);
        }
    }

    private double calculatePriceDifference(DrinkType type , double money) {
        return money - type.getPrice();
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
