package com.yoon.TExpress.chickenAnnotation;

import java.util.Arrays;
import java.util.List;

@Chicken("양념")
public class ChickenRestaurant<@Chicken("양념") T> {

    public static void main(@Chicken("양념") String[] args) throws @Chicken("양념") RuntimeException{
        List<@Chicken("양념") String> names = Arrays.asList("yoonji");
    }
    public static <@Chicken("양념") C> void print(C c){
        System.out.println(c);
    }
}
