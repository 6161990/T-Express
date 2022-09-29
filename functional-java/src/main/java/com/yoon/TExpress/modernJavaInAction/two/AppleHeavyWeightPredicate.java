package com.yoon.TExpress.modernJavaInAction.two;

public class AppleHeavyWeightPredicate implements PredicateAppleFilter{

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
