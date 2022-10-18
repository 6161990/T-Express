package com.yoon.TExpress.modernJavaInAction.six;

import com.yoon.TExpress.modernJavaInAction.four.Dish;
import com.yoon.TExpress.modernJavaInAction.four.FoodType;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.yoon.TExpress.modernJavaInAction.four.FoodType.*;
import static com.yoon.TExpress.modernJavaInAction.four.FoodType.FISH;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
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

    private static Map<String, List<String>> dishTags = new HashMap<>();

    static {
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));
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
        Map<FoodType, List<Dish>> map = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType)); // groupingBy(f) 는 groupingBy(f, toList()) 의 축약형이다

        System.out.println(map); // FISH 요소 값이 없으면 FISH 출력되지 않음

        Map<FoodType, List<Dish>> map2 = menu.stream().collect(groupingBy(Dish::getType,
                filtering(dish -> dish.getCalories() > 500, toList())));

        System.out.println(map2); // FISH 요소 값이 없으면 FISH=[] 로 출력됨
        // {MEAT=[Dish(name=pork, vegetarian=false, calories=800, type=MEAT), Dish(name=beef, vegetarian=false, calories=700, type=MEAT)], OTHER=[Dish(name=french fries, vegetarian=true, calories=530, type=OTHER), Dish(name=pizza, vegetarian=true, calories=550, type=OTHER)], FISH=[]}
    }

    @Test
    void step4_grouping_with_mapping() {
        Map<FoodType, List<String>> map
                = menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

        System.out.println(map);
        // {MEAT=[pork, beef, chicken], OTHER=[french fries, rice, season fruit, pizza], FISH=[prawns, salmon]}
    }

    @Test
    void step5_grouping_with_mapping2() {
        Map<FoodType, Set<String>> mapSet = menu.stream()
                .collect(groupingBy(Dish::getType, flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));

        System.out.println(mapSet);
    }

    @Test
    void step6_multi_grouping() {
        Map<FoodType, Map<CaloricLevel, List<Dish>>> dishedByTypeCaloricLevel = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }))
        );

        System.out.println(dishedByTypeCaloricLevel);
    }

    @Test
    void step7_grouping_counting() {
        Map<FoodType, Long> collect = menu.stream().collect(groupingBy(Dish::getType, counting()));

        System.out.println(collect); // {OTHER=4, MEAT=3, FISH=2}
    }

    @Test
    void step8_grouping_optional() {
        Map<FoodType, Optional<Dish>> collect
                = menu.stream()
                .collect(groupingBy(Dish::getType, maxBy(comparing(Dish::getCalories))));

        System.out.println(collect); // {MEAT=Optional[Dish(name=pork, vegetarian=false, calories=800, type=MEAT)], FISH=Optional[Dish(name=salmon, vegetarian=false, calories=450, type=FISH)], OTHER=Optional[Dish(name=pizza, vegetarian=true, calories=550, type=OTHER)]}
    }

    @Test
    void step9_collectingAndThen() {
        Map<FoodType, Dish> collect
                = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        // 컬렉터 결과를 다른 형식에 적용하기

        System.out.println(collect);
    }
}
