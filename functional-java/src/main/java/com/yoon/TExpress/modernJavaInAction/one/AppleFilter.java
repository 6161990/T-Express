package com.yoon.TExpress.modernJavaInAction.one;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    static List<Apple> filterApples(List<Apple> inventory,
                                    Predicate<Apple> predicate){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(predicate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    static List<Apple> andFilterDefaultHeavy(List<Apple> inventory,
                                             Predicate<Apple> predicate){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            Predicate<Apple> and = predicate.and(Apple::isHeavyApple); /** 필수로 걸러야할 필터를 여기에 미리 해놓아도 될 듯 */
            if(and.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    static List<Apple> orFilterDefaultHeavy(List<Apple> inventory,
                                             Predicate<Apple> predicate){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            Predicate<Apple> and = predicate.or(Apple::isHeavyApple); /** 필수로 걸러야할 필터를 여기에 미리 해놓아도 될 듯 */
            if(and.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    static List<Apple> negativeFilterDefaultHeavy(List<Apple> inventory,
                                            Predicate<Apple> predicate){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            Predicate<Apple> negate = predicate.negate().and(Predicate.not(Apple::isHeavyApple)); /** default negative 필터는 Predicate.not 으로!  */
            if(negate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

}
