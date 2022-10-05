package com.yoon.TExpress.modernJavaInAction.three;

import com.yoon.TExpress.modernJavaInAction.two.Apple;
import org.junit.jupiter.api.Test;

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


    }
}
