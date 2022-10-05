package com.yoon.TExpress.modernJavaInAction.three;

import com.yoon.TExpress.modernJavaInAction.two.Apple;
import com.yoon.TExpress.modernJavaInAction.two.Color;
import com.yoon.TExpress.modernJavaInAction.two.Fruit;
import com.yoon.TExpress.modernJavaInAction.two.Orange;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static org.assertj.core.api.Assertions.assertThat;

class MethodReference {

    @Test
    void methodReferenceTest() {
        ToIntFunction<String> stringToInt =
                (String s) -> Integer.parseInt(s);

        Function<String, Integer> stringToInt2 =
                Integer::parseInt;

        int applied1 = stringToInt.applyAsInt("2");
        Integer applied2 = stringToInt2.apply("2");

        assertThat(applied1).isEqualTo(applied2);
    }

    @Test
    void constructReferenceTest_supplier() {
        Supplier<Apple> c1 = Apple::new;
        Apple apple = c1.get();

        Supplier<Apple> c2 = () -> new Apple();
        Apple apple2 = c2.get();
    }

    @Test
    void constructReferenceTest_function() {
        Function<Integer, Apple> c1 = Apple::new;
        Apple apply = c1.apply(110);

        Function<Integer, Apple> c2 = (weight) -> new Apple(weight);
        Apple apply1 = c2.apply(110);

        assertThat(apply.getWeight()).isEqualTo(apply1.getWeight());
    }

    @Test
    void FunctionTest() {
        List<Integer> integers = Arrays.asList(120, 40, 190);
        List<Apple> map = map(integers, Apple::new);

        assertThat(map).containsExactly(new Apple(120), new Apple(40), new Apple(190));
    }

    public List<Apple> map(List<Integer> list, Function<Integer, Apple> f){
        List<Apple> result = new ArrayList<>();
        for (Integer i : list){
            result.add(f.apply(i));
        }
        return result;
    }

    @Test
    void BiFunctionTest() {
        BiFunction<Integer, Color, Apple> c3 = Apple::new;
        Apple apply = c3.apply(110, Color.RED);

        BiFunction<Integer, Color, Apple> c4 = (integer, color) -> new Apple(integer, color);
        Apple apply1 = c4.apply(110, Color.RED);

        assertThat(apply).isEqualTo(apply1);
    }

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    public static Fruit giveMeFruit(String fruit, Integer weight){
        return map.get(fruit.toLowerCase()).apply(weight);
    }

    @Test
    void notInstance_FunctionTest() {
        Fruit orange = giveMeFruit("orange", 340);

        assertThat(orange).isEqualTo(new Orange(340));
    }
}
