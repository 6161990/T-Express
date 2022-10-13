package com.yoon.TExpress.modernJavaInAction.six;

import com.yoon.TExpress.modernJavaInAction.four.Dish;
import com.yoon.TExpress.modernJavaInAction.four.FoodType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yoon.TExpress.modernJavaInAction.four.FoodType.*;
import static com.yoon.TExpress.modernJavaInAction.four.FoodType.FISH;
import static java.util.stream.Collectors.groupingBy;

public class Grouping {

    private static List<Dish> menu = new ArrayList<>();

    static {
        menu.add(new Dish("pork", false, 800, MEAT));
        menu.add(new Dish("beef", false, 700, MEAT));
        menu.add(new Dish("chicken", false, 400, MEAT));
        menu.add(new Dish("french fries", true, 530, OTHER));
        menu.add(new Dish("rice", true, 350, OTHER));
        menu.add(new Dish("season fruit", true, 120, OTHER));
        menu.add(new Dish("pizza", true, 550, OTHER));
        menu.add(new Dish("prawns", false, 300, FISH));
        menu.add(new Dish("salmon", false, 450, FISH));
    }

    @Test
    void step1_grouping() {
        Map<FoodType, List<Dish>> map = menu.stream().collect(groupingBy(Dish::getType));

        System.out.println(map);
    }
}
