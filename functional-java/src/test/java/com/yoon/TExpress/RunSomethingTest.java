package com.yoon.TExpress;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RunSomethingTest {

    @Test
    void Functional_Interface_익명_내부_클래스로_구현한다() {
        RunSomething runSomething = new RunSomething() {
            @Override
            public String doSomething() {
                return "Hi";
            }
        };

        String actual = runSomething.doSomething();

        Assertions.assertEquals(actual, "Hi");
    }

    @Test
    void 람다_표현식() {
        RunSomething runSomething = () ->"Hi2";

        String actual = runSomething.doSomething();

        Assertions.assertEquals(actual, "Hi2");
    }

    @Test
    void Functional_Interface_익명_내부_클래스로_구현한다2() {
        RunSomething runSomething = new RunSomething() {
            @Override
            public String doSomething() {
                return "Hi YoonJi";
            }
        };

        String actual = runSomething.doSomething();

        Assertions.assertEquals(actual, "Hi YoonJi");
    }

    @Test
    void 람다_표현식2() {
        RunSomething runSomething = () -> "Hi" + " YoonJi2";

        String actual = runSomething.doSomething();

        Assertions.assertEquals(actual, "Hi YoonJi2");
    }


}
