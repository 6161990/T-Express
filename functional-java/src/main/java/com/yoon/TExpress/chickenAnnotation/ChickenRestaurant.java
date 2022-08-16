package com.yoon.TExpress.chickenAnnotation;

import java.util.Arrays;
import java.util.List;

@Chicken
public class ChickenRestaurant<@Chicken T> {

    public static void main(@Chicken String[] args) throws @Chicken RuntimeException{
        List<@Chicken String> names = Arrays.asList("yoonji");
    }
    public static <@Chicken C> void print(C c){
        System.out.println(c);
    }
}
