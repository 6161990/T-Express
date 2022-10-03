package com.yoon.TExpress.modernJavaInAction.three;

import com.yoon.TExpress.modernJavaInAction.two.Apple;
import com.yoon.TExpress.modernJavaInAction.two.Color;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
}
