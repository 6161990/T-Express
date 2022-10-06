package com.yoon.TExpress.modernJavaInAction.four;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {

    private static List<Dish> menu = new ArrayList<>();

    static {
        menu.add(new Dish("pork", false, 800, FoodType.MEAT));
        menu.add(new Dish("beef", false, 700, FoodType.MEAT));
        menu.add(new Dish("chicken", false, 400, FoodType.MEAT));
        menu.add(new Dish("french fries", true, 530, FoodType.OTHER));
        menu.add(new Dish("rice", true, 350, FoodType.OTHER));
        menu.add(new Dish("season fruit", true, 120, FoodType.OTHER));
        menu.add(new Dish("pizza", true, 550, FoodType.OTHER));
        menu.add(new Dish("prawns", false, 300, FoodType.FISH));
        menu.add(new Dish("salmon", false, 450, FoodType.FISH));
    }

    @Test
    void name() {

    }
}
