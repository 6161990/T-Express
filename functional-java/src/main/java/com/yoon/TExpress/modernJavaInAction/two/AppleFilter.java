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

    public static List<Apple> filterApples(List<Apple> inventory, Color color){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory){
            if(color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory){
            if(flag && color.equals(apple.getColor()) || !flag && apple.getWeight() > weight){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, PredicateAppleFilter appleFilter){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory){
            if(appleFilter.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<String> filterApples(List<Apple> inventory, AppleFormatter appleFormatter){
        List<String> result = new ArrayList<>();
        for (Apple apple : inventory){
            String accept = appleFormatter.accept(apple);
            result.add(accept);
        }
        return result;
    }

}
