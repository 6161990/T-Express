package com.yoon.TExpress.modernJavaInAction.three;

import org.junit.jupiter.api.Test;

public class LambdaTest {

    @Test
    void RunnableTest() {
        Runnable r1 = () -> System.out.println("Hello world.");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world2.");
            }
        };

        process(r1);
        process(r2);
        process(()-> System.out.println("Hello world3."));
    }

    public static void process(Runnable r){
        r.run();
    }
}
