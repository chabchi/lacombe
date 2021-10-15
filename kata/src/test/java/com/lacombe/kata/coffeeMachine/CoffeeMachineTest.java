package com.lacombe.kata.coffeeMachine;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Test;


public class CoffeeMachineTest {

    private static final String TEA_WITH_ONE_SUGAR = "T:1:0";
    private static final String CHOCOLATE_WITH_NO_SUGAR = "H::";
    private static final String COFFEE_WITH_TWO_SUGAR = "C:2:0";
    private static final String FORWARDED_MESSAGE = "M:message-content";
    private static final String YOU_ONLY_MISSES_AMOUNT_MESSAGE = "M:You only misses 0.2 euro to get your drink!";
    private static final String ORANGE_JUICE_WITH_TWO_SUGAR = "O::";
    private static final String HOT_COFFEE_WITH_NO_SUGAR = "Ch::";
    private static final String HOT_CHOCOLATE_WITH_NO_SUGAR = "Hh:1:0";
    private static final String HOT_TEA_WITH_TWO_SUGAR = "Th:2:0";



    @Test
    public void should_make_tea_with_one_sugar_and_a_stick() {
        // Given
        String type = "T";
        String sugarQuantity = "1";
        Drink drink = new Drink(type, sugarQuantity);
        double money = 1;

        // When
        String result = drink.makeCoffee(money);

        // Then
        Assertions.assertThat(result).isEqualTo(TEA_WITH_ONE_SUGAR);

    }

    @Test
    public void should_make_chocolate_with_no_sugar_and_a_no_stick() {
        // Given
        String type = "H";
        String sugarQuantity = "0";
        Drink drink = new Drink(type, sugarQuantity);
        double money = 1;

        // When
        String result = drink.makeCoffee(money);

        // Then
        Assertions.assertThat(result).isEqualTo(CHOCOLATE_WITH_NO_SUGAR);

    }

    @Test
    public void should_make_coffee_with_two_sugar_and_a_stick() {
        // Given
        String type = "C";
        String sugarQuantity = "2";
        Drink drink = new Drink(type, sugarQuantity);
        double money = 1;

        // When
        String result = drink.makeCoffee(money);

        // Then
        Assertions.assertThat(result).isEqualTo(COFFEE_WITH_TWO_SUGAR);

    }

    @Test
    public void should_forwards_message() {
        // Given
        String type = "M";
        String content = "message-content";
        Drink drink = new Drink(type, content);
        double money = 1;

        // When
        String result = drink.makeCoffee(money);

        // Then
        Assertions.assertThat(result).isEqualTo(FORWARDED_MESSAGE);

    }

    @Test
    public void should_throw_unknown_coffee_type_exception() {
        // Given
        String type = "S";
        String sugarQuantity = "1";

        // When
        Throwable thrown = ThrowableAssert.catchThrowable(() -> new Drink(type, sugarQuantity)) ;

        // Then
        Assertions.assertThat(thrown).isNotNull().isInstanceOf(CoffeeInstructionException.class);

    }

    @Test
    public void should_throw_unvalid_sugarQuantity_exception() {
        // Given
        String type = "C";
        String sugarQuantity = "Some text";

        // When
        Throwable thrown = ThrowableAssert.catchThrowable(() -> new Drink(type, sugarQuantity)) ;

        // Then
        Assertions.assertThat(thrown).isNotNull().isInstanceOf(CoffeeInstructionException.class);

    }

    @Test
    public void should_send_not_enough_money_message() {
        // Given
        String type = "T";
        String sugarQuantity = "1";
        Drink drink = new Drink(type, sugarQuantity);
        double money = 0.2;

        // When
        String result = drink.makeCoffee(money);

        // Then
        Assertions.assertThat(result).isEqualTo(YOU_ONLY_MISSES_AMOUNT_MESSAGE);

    }

    @Test
    public void should_make_orange_juice_with_no_sugar_and_a_no_stick() {
        // Given
        String type = "O";
        String sugarQuantity = "0";
        Drink drink = new Drink(type, sugarQuantity);
        double money = 1;

        // When
        String result = drink.makeCoffee(money);

        // Then
        Assertions.assertThat(result).isEqualTo(ORANGE_JUICE_WITH_TWO_SUGAR);

    }

    @Test
    public void should_make_extra_hot_coffee_with_no_sugar() {
        // Given
        String type = "C";
        String sugarQuantity = "0";
        boolean hot = true;
        Drink drink = new Drink(type, sugarQuantity, hot);
        double money = 1;

        // When
        String result = drink.makeCoffee(money);

        // Then
        Assertions.assertThat(result).isEqualTo(HOT_COFFEE_WITH_NO_SUGAR);

    }

    @Test
    public void should_make_extra_hot_chocolate_with_one_sugar_and_stick() {
        // Given
        String type = "H";
        String sugarQuantity = "1";
        boolean hot = true;
        Drink drink = new Drink(type, sugarQuantity, hot);
        double money = 1;

        // When
        String result = drink.makeCoffee(money);

        // Then
        Assertions.assertThat(result).isEqualTo(HOT_CHOCOLATE_WITH_NO_SUGAR);

    }

    @Test
    public void should_make_extra_hot_tea_with_two_sugar_and_stick() {
        // Given
        String type = "T";
        String sugarQuantity = "2";
        boolean hot = true;
        Drink drink = new Drink(type, sugarQuantity, hot);
        double money = 1;

        // When
        String result = drink.makeCoffee(money);

        // Then
        Assertions.assertThat(result).isEqualTo(HOT_TEA_WITH_TWO_SUGAR);

    }

}