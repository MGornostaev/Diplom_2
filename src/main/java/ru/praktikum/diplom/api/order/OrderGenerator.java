package ru.praktikum.diplom.api.order;

import java.util.Random;

import static ru.praktikum.diplom.api.order.OrderIngredientsHash.INGREDIENTS;

public class OrderGenerator {
    public Order generic() {
        return new Order(new String[]{"61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa70", "61c0c5a71d1f82001bdaaa74"});
    }

    public Order empty() {
        return new Order(null);
    }

    public Order invalid() {
        return new Order(new String[]{"61c0c5a71d1f82201bdaaa33", "55c0c5a71d1f82201bdaaa32"});
    }

    public Order random() {
        Random generator = new Random();
        int randomIndexOne = generator.nextInt(INGREDIENTS.length);
        int randomIndexTwo = generator.nextInt(INGREDIENTS.length);
        int randomIndexThree = generator.nextInt(INGREDIENTS.length);

        return new Order(new String[]{INGREDIENTS[randomIndexOne], INGREDIENTS[randomIndexTwo], INGREDIENTS[randomIndexThree],  });
    }
}
