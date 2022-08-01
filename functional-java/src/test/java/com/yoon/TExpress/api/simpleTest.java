package com.yoon.TExpress.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class simpleTest {

    @Test
    void forEachTest() {
        List<String> name = getExamList();

        name.forEach(System.out::println);

        System.out.println("==============");

        /** 같은 방법으로 이런 것도 있다. */
        for (String s : name) {
            System.out.println(s);
        }
    }

    @DisplayName("쪼갤 수 있는 iterator")
    @Test
    void spliterator() {
        List<String> name = getExamList();

        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("=================");
        while (spliterator1.tryAdvance(System.out::println));
    }

    @Test
    void Stream() {
        List<String> name = getExamList();

        List<String> collect = name.stream().map(String::toUpperCase)
                .filter(i -> i.length() > 5)
                .collect(Collectors.toList());

        System.out.println(collect); // [YOONJI, SARANG]
    }

    @Test
    void removeIf() {
        List<String> name = getExamList();

        name.removeIf(s->s.startsWith("s"));

        name.forEach(System.out::println);
    }

    @Test
    void comparator() {
        List<String> name = getExamList();
        name.add("foo");

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed());

        name.forEach(System.out::println);
    }

    public List<String> getExamList() {
        List<String> name = new ArrayList<>();
        name.add("yoonji");
        name.add("sarang");
        name.add("rose");
        name.add("jenny");
        return name;
    }

}
