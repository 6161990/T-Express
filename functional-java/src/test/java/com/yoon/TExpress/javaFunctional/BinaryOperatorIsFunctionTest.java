package com.yoon.TExpress.javaFunctional;

import com.yoon.TExpress.javaFunctional.SupplierIsFunctionTest.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryOperatorIsFunctionTest {

    /**
     * BinaryOperator :
     * Type T의 인자 두개를 받고, 동일한 Type T 객체를 리턴하는 함수형 인터페이스
     * */

    @Test
    void Binary_Operator() {
        BinaryOperator<Integer> binaryOperator = (n1, n2) -> n1 + n2;

        Integer sum = binaryOperator.apply(10, 100);

        assertEquals(sum, 110);
    }

    @DisplayName("Comparator 를 이용하여 두개의 객체를 비교하고 큰 값을 리턴하는 BinaryOperator 를 생성")
    @Test
    void maxBy() {
        BinaryOperator<Item> binaryOperator = BinaryOperator.maxBy(
                (Item i1, Item i2) -> i1.getAge() - i2.getAge());

        Item item1 = new Item(10, "me");
        Item item2 = new Item(20, "you");

        Item max = binaryOperator.apply(item1, item2);

        Assertions.assertEquals(max.toString(), "name=you, age=20");
    }

    @DisplayName("Comparator 를 이용하여 두개의 객체를 비교하고 작은 값을 리턴하는 BinaryOperator 를 생성")
    @Test
    void minBy() {
        BinaryOperator<Item> binaryOperator = BinaryOperator.minBy(
                (Item i1, Item i2) -> i1.getAge() - i2.getAge());

        Item item1 = new Item(10, "me");
        Item item2 = new Item(20, "you");

        Item max = binaryOperator.apply(item1, item2);

        Assertions.assertEquals(max.toString(), "name=me, age=10");
    }
}
