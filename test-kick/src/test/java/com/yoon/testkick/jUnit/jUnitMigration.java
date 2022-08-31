package com.yoon.testkick.jUnit;

import org.junit.Before;
import org.junit.Test;

public class jUnitMigration {

    @Before
    public void before(){
        System.out.println("before");
    }

    @Test
    public void createTestWithJunit4(){
        System.out.println("test");
    }

    @Test
    public void createTestWithJunit4_2(){
        System.out.println("test2");
    }
}
