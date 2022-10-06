package com.yoon.TExpress.modernJavaInAction.four;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    void step1() {
        List<Dish> lowCaloricDishes = new ArrayList<>();

        for (Dish dish : menu){
            if(dish.getCalories() < 400){
                lowCaloricDishes.add(dish);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> lowCaloricDishesName = new ArrayList<>();

        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }

        System.out.println(lowCaloricDishesName);
    }

    @Test
    void step2() {

    }
}
