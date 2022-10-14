package com.yoon.TExpress.modernJavaInAction.six;

import com.yoon.TExpress.modernJavaInAction.four.Dish;
import com.yoon.TExpress.modernJavaInAction.four.FoodType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yoon.TExpress.modernJavaInAction.four.FoodType.*;
import static com.yoon.TExpress.modernJavaInAction.four.FoodType.FISH;
import static java.util.stream.Collectors.*;

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

    @Test
    void step2_grouping() {
        Map<CaloricLevel, List<Dish>> map = menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));

        System.out.println(map);
    }

    @Test
    void step3_grouping_with_filter() {
        Map<FoodType, List<Dish>> map = menu.stream().filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));

        System.out.println(map); // FISH 요소 값이 없으면 FISH 출력되지 않음

        Map<FoodType, List<Dish>> map2 = menu.stream().collect(groupingBy(Dish::getType,
                filtering(dish -> dish.getCalories() > 500, toList())));

        System.out.println(map2); // FISH 요소 값이 없으면 FISH=[] 로 출력됨
    }
}
