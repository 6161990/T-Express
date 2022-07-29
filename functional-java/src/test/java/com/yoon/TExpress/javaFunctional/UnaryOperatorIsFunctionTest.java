package com.yoon.TExpress.javaFunctional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.UnaryOperator;

public class UnaryOperatorIsFunctionTest {

    /**
     * UnaryOperator
     * Type T의 인자 하나를 받고, 동일한 Type T 객체를 리턴하는 함수형 인터페이스
     * */

    @Test
    void UnaryOperator() {
        UnaryOperator<Integer> unaryOperator1 = n -> n*n;

        Integer actual = unaryOperator1.apply(10);

        Assertions.assertEquals(actual, 100);
    }

    @Test
    void UnaryOperator2() {
        UnaryOperator<Boolean> unaryOperator1 = n -> !n;

        Boolean actual = unaryOperator1.apply(true);

        Assertions.assertFalse(actual);
    }

    @Test
    void andThen() {
        UnaryOperator<Integer> func1 = n -> n * 2;
        UnaryOperator<Integer> func2 = n -> n * 3;

        Integer actual = func1.andThen(func2).apply(10);

        Assertions.assertEquals(actual, 60);
    }


}
