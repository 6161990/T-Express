package com.yoon.TExpress.javaFunctional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PredicateIsFunctionTest {

    @Test
    void Predicate_T_T타입의_값을_받아서_boolean을_리턴하는_함수_인터페이스() {
        Predicate<String> startWithYoonji = (s) -> s.startsWith("yoonji");

        boolean actual = startWithYoonji.test("test");
        boolean actual2 = startWithYoonji.test("yoonji");

        Assertions.assertFalse(actual);
        Assertions.assertTrue(actual2);

        Predicate<Integer> inEven = (i) -> i%2 == 0;
        boolean actual3 = inEven.test(30);
        Assertions.assertTrue(actual3);
    }

    @Test
    void and_Predicate를_연결하는_chain_함수() {
        Predicate<Integer> predicate1 = (num) -> num > 10;
        Predicate<Integer> predicate2 = (num) -> num < 20;

        /**
         *  predicate1과 predicate2가 모두 true 일 때 true 가 리턴
         * */
        boolean actual = predicate1.and(predicate2).test(25);
        boolean actual2 = predicate1.and(predicate2).test(19);

        Assertions.assertFalse(actual);
        Assertions.assertTrue(actual2);
    }

    @Test
    void or_Predicate_를_연결하는_chain_함수() {
        Predicate<Integer> predicate1 = (num) -> num > 10;
        Predicate<Integer> predicate2 = (num) -> num < 0;

        /**
         *  predicate1과 predicate2중 하나라도 true 일 때 true 가 리턴
         * */
        boolean actual = predicate1.or(predicate2).test(11);
        boolean actual2 = predicate1.or(predicate2).test(5);

        Assertions.assertTrue(actual);
        Assertions.assertFalse(actual2);
    }

    @Test
    void isEqual() {
        Stream<Integer> stream = IntStream.range(1, 10).boxed();

        stream.filter(Predicate.isEqual(5))
                .forEach(System.out::println);
        stream.filter(Predicate.isEqual(7))
                .forEach(System.out::println);
    }

    @Test
    void negate() {
        /**
         *  Predicate 가 리턴하는 값과 반대되는 값을 리턴
         * */
        Predicate<Integer> predicate = (num) -> num > 10;
        Predicate<Integer> negatePredicate = predicate.negate();

        boolean actual = predicate.test(100);
        boolean actual2 = negatePredicate.test(100);

        Assertions.assertTrue(actual);
        Assertions.assertFalse(actual2);
    }

    @Test
    void Stream() {
        Stream<Integer> stream = IntStream.range(1, 10).boxed();

        Predicate<Integer> predicate = num -> num > 5;

        stream.filter(predicate).forEach(System.out::println);
    }
}
