package com.yoon.TExpress.javaFunctional;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class EffectiveLocalVar {

    @Test
    void name() {
        Foo foo = new Foo();
        foo.run();

    }


    private class Foo {

        public void run(){
            int baseNumber = 10;

            class LocalClass {
                void printBaseNumber() {
                    int baseNumber = 11;
                    System.out.println(baseNumber);
                }
            }

            Consumer<Integer> integerConsumer = new Consumer<Integer>() {
                @Override
                public void accept(Integer baseNumber) {
                    System.out.println(baseNumber);
                }
            };

            IntConsumer printInt = (i) -> {
                System.out.println(i + baseNumber);
            };

            printInt.accept(10);
        }
    }
}
