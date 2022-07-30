package com.yoon.TExpress.javaFunctional;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupplierIsFunctionTest {

    @Test
    void Supplier() {
        Supplier<String> supplier = () -> "HelloWorld";

        String actual = supplier.get();

        assertEquals(actual, "HelloWorld");
    }

    @Test
    void Supplier는_제네릭을_구현되어있어_어떤객체이든_리턴할_수_있다() {
        Supplier<Item> supplier = () -> new Item(10, "Hello");

        Item actual = supplier.get();

        assertEquals(actual.toString(), "name=Hello, age=10");
    }

    @Test
    void Supplier_with_method_reference() {
        Supplier<String> supplier = SupplierIsFunctionTest::getHelloWorld;

        String actual = supplier.get();

        assertEquals(actual, "Hello World");
    }

    public static String getHelloWorld() {
        return "Hello World";
    }

    @Test
    void Primitive_Suppler() {
        String hello = "hello";
        BooleanSupplier booleanSupplier = () -> hello.equals("world");
        IntSupplier intSupplier = () -> hello.length();
        LongSupplier longSupplier = () -> hello.length();
        DoubleSupplier doubleSupplier = () -> 12.34 - hello.length();

        assertEquals(booleanSupplier.getAsBoolean(), false);
        assertEquals(intSupplier.getAsInt(), 5);
        assertEquals(longSupplier.getAsLong(), 5L);
        assertEquals(doubleSupplier.getAsDouble(), 7.34);
    }

    /**
     * Stream.generate()는 Supplier를 인자로 받아, 무한한 Stream을 생성.
     * limit() 은 stream 의 제한 갯수
     * */
    @Test
    void Supplier_with_Stream_generate() {
        Supplier<Integer> randomNumberSuppler = () -> new Random().nextInt(100);

        Stream.generate(randomNumberSuppler)
                .limit(4)
                .forEach(System.out::println);
    }

    static class Item {
        private final String name;
        private final int age;

        public Item(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "name=" + name +
                    ", age=" + age ;
        }
    }
}
