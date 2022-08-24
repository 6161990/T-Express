package com.yoon.testkick.jUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestInstanceTest {

    int value = 0 ;

    @Test
    void basic_test_1() {
        System.out.println("Basic basic_test_"+ ++value);
    }

    @Test
    void basic_test_2() {
        System.out.println("Basic basic_test_"+ ++value);
    }

    /**
     * 테스트의 인스턴스 단위가 클래스이기 때문에  BeforeAll, AfterAll 이 static 할 수 없다
     * */
    @BeforeAll
    /* static */void beforeAll() {
        System.out.println("Basic beforeAll ");
    }

    @AfterAll
    /* static */void afterAll() {
        System.out.println("Basic afterAll ");
    }



}
