package com.yoon.TExpress.modernJavaInAction.six;

import com.yoon.TExpress.modernJavaInAction.four.Dish;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.yoon.TExpress.modernJavaInAction.four.FoodType.*;
import static com.yoon.TExpress.modernJavaInAction.four.FoodType.OTHER;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summingInt;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamCollectors {

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
    void step1_reducing() {
        Long howManyDishes = menu.stream().collect(Collectors.counting());
        Long howManyDishes2 = menu.stream().count();

        assertThat(howManyDishes).isEqualTo(9);
        assertThat(howManyDishes).isEqualTo(howManyDishes2);
    }

    @Test
    void step2_maxBy_minBy() {
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishComparator));
        Optional<Dish> mostCalorieDish2 = menu.stream().max(dishComparator);

        assertThat(mostCalorieDish.get().getName()).isEqualTo("pork");
        assertThat(mostCalorieDish2.get().getName()).isEqualTo("pork");
    }

    @Test
    void step3_summingInt() {
        Integer totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        Integer totalCalories2 = menu.stream().mapToInt(Dish::getCalories).sum();

        assertThat(totalCalories).isEqualTo(4200);
        assertThat(totalCalories).isEqualTo(totalCalories2);
    }
}
