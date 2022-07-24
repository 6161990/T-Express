package com.yoon.TExpress;

import org.junit.jupiter.api.Test;

public class RunSomethingTest {

    @Test
    void Functional_Interface_익명_내부_클래스로_구현한다() {
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doSomething() {
                System.out.println("Hi");
            }
        };
    }

    @Test
    void 람다_표현식() {
        RunSomething runSomething = () -> System.out.println("Hi");
    }

    @Test
    void Functional_Interface_익명_내부_클래스로_구현한다2() {
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doSomething() {
                System.out.println("Hi");
                System.out.println("YoonJi");
            }
        };
    }

    @Test
    void 람다_표현식2() {
        RunSomething runSomething = () -> {
            System.out.println("Hi");
            System.out.println("YoonJi");
        };
    }

    @Test
    void Functional_Interface() {
        RunSomething runSomething = () -> System.out.println("Hi");
        runSomething.doSomething();
    }


}
