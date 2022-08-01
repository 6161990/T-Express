package com.yoon.TExpress.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    void parallelStream() {
        List<String> names = getExamList();

        List<String> collect = names.parallelStream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName()); // ForkJoinPool.commonPool-worker-5
            return s.toUpperCase();
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

    @Test
    void stream() {
        List<String> names = getExamList();

        List<String> collect = names.stream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName()); // yoonji Test worker
            return s.toUpperCase();
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);
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
