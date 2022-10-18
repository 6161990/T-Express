package com.yoon.TExpress.modernJavaInAction.four;

import lombok.Value;

@Value(staticConstructor = "of")
public class Dish {
    String name;
    boolean vegetarian;
    int calories;
    FoodType type;

    public Dish(String name, boolean vegetarian, int calories, FoodType type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
