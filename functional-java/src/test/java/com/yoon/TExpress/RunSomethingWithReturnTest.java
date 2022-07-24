package com.yoon.TExpress;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunSomethingWithReturnTest {

    @Test
    void Functional_Interface_는_순수함수다(){
        RunSomethingWithReturn runSomething = number -> number+10;

        int i = runSomething.doSomethingWithReturn(1);
        int i1 = runSomething.doSomethingWithReturn(1);
        int i2 = runSomething.doSomethingWithReturn(1);

        Assertions.assertEquals(i, i1);
        Assertions.assertEquals(i1, i2);
    }

    @Test
    void 함수형_인터페이스_구현이_함수_바깥에_존재하는_변수에_의지하고있다_순수하지않다() {
        RunSomethingWithReturn runSomethingWithReturn = new RunSomethingWithReturn() {
            int baseNumber = 10;

            @Override
            public int doSomethingWithReturn(int number) {
                return number + baseNumber;
            }
        };

    }
}