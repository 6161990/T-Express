package com.yoon.TExpress.modernJavaInAction.three;

import com.yoon.TExpress.modernJavaInAction.two.Apple;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.*;

import static com.yoon.TExpress.modernJavaInAction.two.Color.GREEN;
import static com.yoon.TExpress.modernJavaInAction.two.Color.RED;
import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionalInterfaceExamTest {

    @Test
    void PredicateTest() {
        List<String> emptyList = asList();

        assertTrue(filter(emptyList, (List<String> list) -> list.isEmpty()));
    }

    private boolean filter(List<String> stringList, Predicate<List<String>> predicate) {
        return predicate.test(stringList);
    }

    @Test
    void SupplierTest() {
        Apple apple = create(() -> new Apple(10, RED));

        assertThat(apple.getColor()).isEqualTo(RED);
    }

    private Apple create(Supplier<Apple> supplier) {
        return supplier.get();
    }

    @Test
    void ConsumerTest() {
        Apple apple = new Apple(120, GREEN);

        accept(apple, (Apple a) -> System.out.println(a.getWeight()));
    }

    private void accept(Apple apple, Consumer<Apple> appleConsumer) {
        appleConsumer.accept(apple);
    }

    @Test
    void FunctionTest() {
        int function = function("Today is be happy !!! :)", (String s) -> s.length());

        assertThat(function).isEqualTo(24);
    }

    private int function(String sentence, Function<String, Integer> function) {
        return function.apply(sentence);
    }

    @Test
    void BinaryOperatorTest() {
        int result = binaryOper(1, 4, (Integer a, Integer b) -> a * b);

        assertThat(result).isEqualTo(4);
    }

    private int binaryOper(int i, int i1, BinaryOperator<Integer> binaryOperator) {
        return binaryOperator.apply(i, i1);
    }

    @Test
    void BiFunctionTest() {
        Apple apple = new Apple(120, GREEN);
        Apple apple2 = new Apple(64, GREEN);

        Integer b = compareWeight(apple, apple2, (Apple a1, Apple a2) -> Integer.compare(a1.getWeight(), a2.getWeight()));

        assertThat(b).isEqualTo(1);
    }

    private Integer compareWeight(Apple apple, Apple apple2, BiFunction<Apple, Apple, Integer> compare) {
        return compare.apply(apple, apple2); /**  비교시 앞에 값이 크면 1 */
    }
}
