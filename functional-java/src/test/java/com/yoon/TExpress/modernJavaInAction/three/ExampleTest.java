package com.yoon.TExpress.modernJavaInAction.three;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.*;
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

}
