package com.yoon.TExpress;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("함수형 인터페이스 주의할 점")
    @Test
    void 함수형_인터페이스_구현이_함수_바깥에_존재하는_변수에_의지하고있다_순수하지않다() {
        RunSomethingWithReturn runSomethingWithReturn = new RunSomethingWithReturn() {
            int baseNumber = 10; /** Field 'baseNumber' may be 'final'  */

            @Override
            public int doSomethingWithReturn(int number) {
                return number + baseNumber;
            }
        };

        int actual = runSomethingWithReturn.doSomethingWithReturn(7);

        Assertions.assertEquals(actual, 17);
    }

    @DisplayName("고차함수 : 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수 있다")
    @Test
    void Functional_Interface_는_고차함수다() {
        RunSomethingWithReturn sut = number -> number + 777;

        int actual = sut.doSomethingWithReturn(1);

        Assertions.assertEquals(actual, 778);
    }
}