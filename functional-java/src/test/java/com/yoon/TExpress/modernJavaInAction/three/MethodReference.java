package com.yoon.TExpress.modernJavaInAction.three;

import com.yoon.TExpress.modernJavaInAction.two.Apple;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    
}
