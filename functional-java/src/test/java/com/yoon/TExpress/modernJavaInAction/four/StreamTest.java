package com.yoon.TExpress.modernJavaInAction.four;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static com.yoon.TExpress.modernJavaInAction.four.FoodType.*;
import static com.yoon.TExpress.modernJavaInAction.four.FoodType.OTHER;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamTest {

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
        List<String> lowCaloricDishesName
                = menu.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());

        System.out.println(lowCaloricDishesName);
    }

    @Test
    void step3() {
        List<String> lowCaloricDishesName
                = menu.parallelStream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());

        System.out.println(lowCaloricDishesName);
    }

    @Test
    void step4() {
        List<String> highCaloricDishesNames
                = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        System.out.println(highCaloricDishesNames); // [pork, beef, chicken]
    }

    @Test
    void step5() {
        List<String> highCaloricDishesNames
                = menu.stream()
                .filter(d -> {
                    System.out.println("filtering : " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping : " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());

        System.out.println(highCaloricDishesNames); // [pork, beef, chicken]
    }

    @Test
    void step6() {
        long count = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .distinct() // 고유 요소로 필터링
                .limit(3)
                .count();

        System.out.println(count); // 3
    }

    private static List<Dish> specialMenu = new ArrayList<>();

    static {
        specialMenu.add(new Dish("season fruit", true, 120, OTHER));
        specialMenu.add(new Dish("prawns", false, 300, FISH));
        specialMenu.add(new Dish("rice", true, 350, OTHER));
        specialMenu.add(new Dish("chicken", false, 400, MEAT));
        specialMenu.add(new Dish("french fries", true, 530, OTHER));
    }

    @Test
    void step7_streamSlicing_takeWhile() {
        List<Dish> dishes = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320) // '정렬된 리스트를' 돌면서 조건과 맞지 않으면 바로 return
                .collect(toList());

        System.out.println(dishes); // fruit , prawns
    }

    @Test
    void step8_streamSlicing_dropWhile() {
        List<Dish> dishes = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320) // 조건과 거짓이 되는 지점까지의 발견 요소를 모두 버림
                .collect(toList());

        System.out.println(dishes); // rice , chicken , french fries
    }

    @Test
    void step9_streamSlicing_skip() {
        List<Dish> dishes = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 320)
                .skip(2)
                .collect(toList());

        System.out.println(dishes); // french fries
    }

    private static List<String> words = new ArrayList<>();

    static {
        words.add("season fruit");
        words.add("prawns");
        words.add("rice");
        words.add("chicken");
        words.add("french fries");
    }

    @Test
    void step10_map() {
        List<Integer> wordLength = words.stream()
                .map(String::length)
                .collect(toList());

        System.out.println(wordLength); // [12, 6, 4, 7, 12]
    }

    @Test
    void step11_스트림_평면화_before() {
        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());

        System.out.println(collect); // List<String[]>
    }

    String[] arrayOfWords = {"GoodBye","World"};

    @Test
    void step11_스트림_평면화_after1() {
        List<String> words = Arrays.asList(arrayOfWords);

        List<Stream<String>> collect = words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());

        System.out.println(collect); // List<Stream<String>>
    }

    @Test
    void step11_스트림_평면화_after2() {
        List<String> words = Arrays.asList(arrayOfWords);

        List<String> collect = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        System.out.println(collect); // List<String> [G, o, d, B, y, e, W, r, l]
    }
}
