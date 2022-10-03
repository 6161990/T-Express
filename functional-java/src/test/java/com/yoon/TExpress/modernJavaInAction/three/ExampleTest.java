package com.yoon.TExpress.modernJavaInAction.three;

import com.yoon.TExpress.modernJavaInAction.two.Apple;
import com.yoon.TExpress.modernJavaInAction.two.Color;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.yoon.TExpress.modernJavaInAction.two.Color.GREEN;
import static com.yoon.TExpress.modernJavaInAction.two.Color.RED;
import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleTest {

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
}
