package com.yoon.TExpress.javaFunctional;

import java.util.function.Function;

public class FunctionIsFunction implements Function<Integer, Integer> {
    /**
     * Function<T, R>
     * */

    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }

    @Override
    public <V> Function<V, Integer> compose(Function<? super V, ? extends Integer> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<Integer, V> andThen(Function<? super Integer, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
