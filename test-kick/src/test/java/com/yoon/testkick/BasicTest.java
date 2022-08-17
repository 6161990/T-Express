package com.yoon.testkick;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BasicTest {

    @Test
    void basic_test_1() {
        Basic basic = new Basic();
        assertNotNull(basic);
        System.out.println("Basic basic_test_1 ");
    }

    @Test
    void basic_test_2() {
        Basic basic = new Basic();
        assertNotNull(basic);
        System.out.println("Basic basic_test_2 ");
    }

    @Test
    @Disabled /** 테스트 실행하지 않는다.*/
    void basic_test_3() {
        Basic basic = new Basic();
        assertNotNull(basic);
        System.out.println("Basic basic_test_3 disabled ");
    }

    /** 해당 테스트 클래스가 실행될 때, 단 한번 실행되는 메소드.
     반드시 static , non-private , no return */
    @BeforeAll
    static void beforeAll() {
        System.out.println("Basic beforeAll ");
    }

    /** 해당 테스트 클래스가 실행된 이후, 단 한번 실행되는 메소드.
     반드시 static , non-private , no return */
    @AfterAll
    static void afterAll() {
        System.out.println("Basic afterAll ");
    }

    /** 각 테스트 메소드가 실행되기 전, 한번씩 실행되는 메소드.
     반드시 non-private , no return */
    @BeforeEach
    void setUp() {
        System.out.println("Basic setUp ");
    }


    /** 각 테스트 메소드가 실행된 후, 한번씩 실행되는 메소드.
     반드시 non-private , no return */
    @AfterEach
    void tearDown() {
        System.out.println("Basic tearDown");
    }
}