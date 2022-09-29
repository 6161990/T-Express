package com.yoon.TExpress.modernJavaInAction.two;

import static com.yoon.TExpress.modernJavaInAction.two.Color.GREEN;

public class AppleGreenAndHeavyWeightPredicate implements PredicateAppleFilter{
    @Override
    public boolean test(Apple apple) {
        return GREEN.equals(apple.getColor()) && apple.getWeight() > 150;
    }
}
