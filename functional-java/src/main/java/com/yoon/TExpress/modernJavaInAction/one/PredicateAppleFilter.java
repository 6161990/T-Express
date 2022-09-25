package com.yoon.TExpress.modernJavaInAction.one;

/** custom 할 수도 있다. */
public interface PredicateAppleFilter<T> {
    boolean test(T t);
}
