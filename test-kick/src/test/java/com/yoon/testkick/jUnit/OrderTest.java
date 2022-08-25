package com.yoon.testkick.jUnit;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderTest {

    int value = 0 ;

    @Test
    @Order(1)
    void basic_test_1() {
        System.out.println("Basic basic_test_"+ ++value);
    }

    @Test
    @Order(2)
    void basic_test_2() {
        System.out.println("Basic basic_test_"+ ++value);
    }

    @Test
    @Order(0)
    void basic_test_3() {
        System.out.println("Basic basic_test_"+ ++value);
    }

   @BeforeAll
   void beforeAll() {
        System.out.println("Basic beforeAll ");
    }

    @AfterAll
   void afterAll() {
        System.out.println("Basic afterAll ");
    }



}
