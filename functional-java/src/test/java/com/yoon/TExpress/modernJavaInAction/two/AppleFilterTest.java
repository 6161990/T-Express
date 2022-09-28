package com.yoon.TExpress.modernJavaInAction.two;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AppleFilterTest {

    private static final Apple APPLE = new Apple(140, Color.GREEN);

    @Test
    void before() {
        List<Apple> greenApplesResult = AppleFilter.filterGreenApples(List.of(APPLE));

        assertThat(greenApplesResult).containsExactly(APPLE);
    }


    @Test
    void after1_컬러필터조건을_파라미터화한다() {
        List<Apple> redAppleResult = AppleFilter.filterGreenApples(List.of(APPLE), Color.RED);

        assertThat(redAppleResult).containsExactly();
    }


}
