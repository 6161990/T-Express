package com.yoon.testkick.jUnit;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(FindSlowTestExtension.class)
public class ExtensionTest {

    @Test
    @FastTest
    void basic_test_1() {
        System.out.println("Basic basic_test_1");
    }

    @Test
    @FastTest
    void basic_test_2() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("Basic basic_test_2");
    }

    @Test
    @SlowTest
    void basic_test_3() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("Basic basic_test_3_already @SlowTest");
    }

}
