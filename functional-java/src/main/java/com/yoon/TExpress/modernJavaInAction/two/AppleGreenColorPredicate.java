package com.yoon.TExpress.modernJavaInAction.two;

import static com.yoon.TExpress.modernJavaInAction.two.Color.GREEN;

public class AppleGreenColorPredicate implements PredicateAppleFilter<Apple>{

    @Override
    public boolean test(Apple apple) {
        return GREEN.equals(apple.getColor());
    }
}
