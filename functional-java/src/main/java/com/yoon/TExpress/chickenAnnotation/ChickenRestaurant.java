package com.yoon.TExpress.chickenAnnotation;

public class ChickenRestaurant<@Chicken T> {

    public static <@Chicken C> void print(C c){
        System.out.println(c);
    }
}
