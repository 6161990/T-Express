package com.yoon.TExpress.javaFunctional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class GreetingTest {

    @Test
    void functional_same_greeting_hi() {
        UnaryOperator<String> hi = (s) -> "hi " + s;

        String actual = hi.apply("yoonji");

        Assertions.assertEquals(actual, "hi yoonji");
    }

    @Test
    void functional_with_Greeting_static_hi() {
        UnaryOperator<String> hi = Greeting::hi;

        String actual = hi.apply("yoonji");

        Assertions.assertEquals(actual, "hi yoonji");
    }

    @Test
    void functional_with_Greeting_instance_hello() {
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;

        String actual = hello.apply("yoonji");

        Assertions.assertEquals(actual, "hello yoonji");
    }

    @Test
    void functional_with_Greeting_noArg() {
        Supplier<Greeting> newGreeting = Greeting::new;

        Greeting actual = newGreeting.get();

        Assertions.assertTrue(actual instanceof Greeting);
    }

    @Test
    void functional_with_Greeting_AllArg() {
        Function<String, Greeting> newGreeting = Greeting::new;

        Greeting actual = newGreeting.apply("yoonji");

        Assertions.assertEquals(actual.getName(), "yoonji");
    }

    @Test
    void 임의_객체의_인스턴스_메소드_참조() {
        String[] names = {"yoonji", "여을심", "kongkong"};
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -1;
            }
        });

        System.out.println(Arrays.toString(names)); // [kongkong, 여을심, yoonji]

        Arrays.sort(names, String::compareToIgnoreCase);

        System.out.println(Arrays.toString(names)); // [kongkong, yoonji, 여을심]
    }
}