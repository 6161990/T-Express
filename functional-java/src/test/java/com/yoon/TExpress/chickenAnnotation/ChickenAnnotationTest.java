package com.yoon.TExpress.chickenAnnotation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ChickenAnnotationTest {

    @Test
    void ChickenTest_byType() {
        Chicken[] chickens = ChickenTest.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c-> System.out.println(c.value()));
    }

    @Test
    void ChickenTest_byAnnotation() {
        ChickenContainer chickens = ChickenTest.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickens.value()).forEach(c-> System.out.println(c.value()));
    }


    @Chicken("마늘간장")
    @Chicken("간장마늘카레")
    class ChickenTest {

    }
}
