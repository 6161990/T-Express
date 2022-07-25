package com.yoon.TExpress.javaFunctional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

class FunctionIsFunctionTest {

    @DisplayName("Function<T,R> - apply")
    @Test
    void Function_예제1() {
        FunctionIsFunction sut = new FunctionIsFunction();

        Integer actual = sut.apply(10);

        Assertions.assertEquals(actual, 20);
    }

    @DisplayName("Function<T,R> - apply (람다 표현식 사용)")
    @Test
    void Function_예제2() {
        Function<Integer, Integer> sut = (i) -> i + 2;

        Integer actual = sut.apply(1);

        Assertions.assertEquals(actual, 3);
    }

    @DisplayName("Function<T,R> - compose")
    @Test
    void Function_예제3() {
        Function<Integer, Integer> plus = (i) -> i + 1;
        Function<Integer, Integer> multiply = (i) -> i * 2;

        Function<Integer, Integer> multiplyAndPlus = plus.compose(multiply);
        Integer actual = multiplyAndPlus.apply(2);

        Assertions.assertEquals(actual, 5);
    }

    @DisplayName("Function<T,R> - AndThen")
    @Test
    void Function_예제4() {
        Function<Integer, Integer> plus = (i) -> i + 1;
        Function<Integer, Integer> multiply = (i) -> i * 2;

        Function<Integer, Integer> plusAndMultiply = plus.andThen(multiply);
        Integer actual = plusAndMultiply.apply(2);

        Assertions.assertEquals(actual, 6);
    }
}
