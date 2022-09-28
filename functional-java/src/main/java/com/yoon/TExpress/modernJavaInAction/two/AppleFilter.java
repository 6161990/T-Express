package com.yoon.TExpress.modernJavaInAction.two;

import java.util.ArrayList;
import java.util.List;

import static com.yoon.TExpress.modernJavaInAction.two.Color.GREEN;

public class AppleFilter {

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory){
            if(GREEN.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory, Color color){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory){
            if(color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }
}
