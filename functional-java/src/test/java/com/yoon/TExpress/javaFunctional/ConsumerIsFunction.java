package com.yoon.TExpress.javaFunctional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class ConsumerIsFunction {

    @Test
    void T타입을_받아서_아무값도_리턴하지않는_소비형_함수인터페이스() {
        /** 리팩토링 to 14 line
         * Consumer<Integer> printT = System.out::println;
         * */
        Consumer<Integer> printT = (i) -> System.out.println(i);

        printT.accept(7); // 7

        Consumer<String> consumer = s -> System.out.println(s.toUpperCase());

        consumer.accept("hello world"); // HELLO WORLD
    }

    @Test
    void primitive_consumer() {
        IntConsumer consumer = n -> System.out.println(n * 100);

        consumer.accept(5);  // 500

        consumer.accept(10); // 1000
    }

    @Test
    void list_forEach() {
        Consumer<String> consumer = s -> System.out.println(s.toUpperCase());

        List<String> list = Arrays.asList("apple", "kiwi", "orange");

        list.forEach(consumer); // APPLE KIWI ORANGE
    }

    @Test
    void andThen() {
        List<String> fruits = Arrays.asList("apple", "kiwi", "orange");

        Consumer<List<String>> addNumber = list -> {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, (i+1) + ". " + list.get(i));
            }
        };

        Consumer<List<String>> printList = list -> {
            for(String fruit : list){
                System.out.println(fruit);
            }
        };

        addNumber.andThen(printList).accept(fruits);
        /**
         * addNumber.andThen(printList).accept(fruits)를 호출하면
         * addNumber()가 수행된 이후에 printList()가 수행.
         * 두개의 메소드로 전달되는 인자는 fruits로, 동일한 객체가 전달됨
         *
         * 1. apple
         * 2. kiwi
         * 3. orange
         * */
    }

    @Test
    void Higher_Order_Function() {
        List<String> fruits = Arrays.asList("apple", "kiwi", "orange");

        Consumer<String> printFruit = fruit -> System.out.println("I like " + fruit);

        forEach(fruits, printFruit);
    }

    private <T> void forEach(List<T> list, Consumer<T> consumer) {
        for(T t : list){
            consumer.accept(t);
        }
    }
    /**
     * I like apple
     * I like kiwi
     * I like orange
     * */


}
