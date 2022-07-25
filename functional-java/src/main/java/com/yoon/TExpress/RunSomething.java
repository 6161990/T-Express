package com.yoon.TExpress;

@FunctionalInterface
public interface RunSomething {

    String doSomething();
    /**
     * 함수형 인터페이스는 반드시 추상 메소드가 하나여야한다.
     * static, default 메소드가 있어도 함수형 인터페이스 라고 할 수 있다.
     * */

    static void printName(){
        System.out.println("yoonJi");
    }

    default void printAge(){
        System.out.println("99999");
    }
}
