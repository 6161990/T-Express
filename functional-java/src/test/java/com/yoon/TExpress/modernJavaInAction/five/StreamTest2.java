package com.yoon.TExpress.modernJavaInAction.five;

import com.yoon.TExpress.modernJavaInAction.four.Dish;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static com.yoon.TExpress.modernJavaInAction.four.FoodType.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamTest2 {

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


    @Test
    void step12_mapping_문제1() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> collect = numbers1.stream()
                .flatMap(
                        i -> numbers2.stream()
                                .map(j -> new int[]{i, j}))
                .collect(toList());

        collect.forEach(System.out::println);
    }

    @Test
    void step12_mapping_문제2() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> collect = numbers1.stream().flatMap(i ->
                        numbers2.stream().filter(j -> (i + j) % 3 == 0)
                                         .map(j -> new int[]{i, j}))
                            .collect(toList());

        collect.forEach(System.out::println);
    }

    @Test
    void step13_reduce1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        int sum = 0;
        for (int i: numbers) {
            sum += i;
        }

        System.out.println(sum);
    }

    @Test
    void step13_reduce2() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Integer sum = numbers.stream().reduce(0, (i1, i2) -> i1 + i2);

        System.out.println(sum);
    }

    @Test
    void step13_reduce2_1_withOptional() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);

        System.out.println(sum.get());
    }

    @Test
    void step13_reduce3() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Integer sum = numbers.stream().reduce(0, Integer::sum);

        System.out.println(sum);
    }

    @Test
    void step13_reduce4() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Integer sum = numbers.stream().reduce(1, (i1, i2) -> i1 * i2);

        System.out.println(sum);
    }

    @Test
    void step13_reduce_max() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Optional<Integer> sum = numbers.stream().reduce(Integer::max);

        System.out.println(sum.get());
    }

    @Test
    void step13_reduce_min() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Optional<Integer> sum = numbers.stream().reduce(Integer::min);
        Optional<Integer> sum2 = numbers.stream().reduce((x, y) -> x < y? x : y);

        assertThat(sum).isEqualTo(sum2);
    }

}
