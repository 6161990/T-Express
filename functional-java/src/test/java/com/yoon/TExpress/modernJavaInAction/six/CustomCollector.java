package com.yoon.TExpress.modernJavaInAction.six;

import com.yoon.TExpress.modernJavaInAction.four.Dish;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.yoon.TExpress.modernJavaInAction.four.FoodType.*;
import static com.yoon.TExpress.modernJavaInAction.four.FoodType.FISH;

public class CustomCollector {

    public static List<Dish> menu = new ArrayList<>();

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
    void step1_custom_collector() {
        List<Dish> collect = menu.stream().collect(new ToListCollector<>());

        System.out.println(collect);
        // [pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
    }

    @Test
    void step2_custom_collector() {
        Map<Boolean, List<Integer>> booleanListMap = partitionPrimesWithCustomCollector(40);

        List<Integer> list = booleanListMap.get(true);
        List<Integer> list2 = booleanListMap.get(false);

        System.out.println(list);
        System.out.println(list2);
    }

    public Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n){
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }
}
