package com.yoon.TExpress.modernJavaInAction.one;

import java.util.ArrayList;
import java.util.List;

public class AppleFilter {

    public static List<Apple> filterHeavyApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if(apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if("GREEN".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
}