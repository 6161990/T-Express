package com.yoon.TExpress.modernJavaInAction.one;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.yoon.TExpress.modernJavaInAction.one.AppleFilter.filterApples;
import static org.assertj.core.api.Assertions.assertThat;

class AppleFilterTest {

    private static final Apple APPLE = new Apple(140, "GREEN");

    @Test
    void before() {
        List<Apple> greenApplesResult = AppleFilter.filterGreenApples(List.of(APPLE));
        List<Apple> heavyApplesResult = AppleFilter.filterHeavyApples(List.of(APPLE));

        assertThat(greenApplesResult).containsExactly(APPLE);
        assertThat(heavyApplesResult).isEmpty();
    }

    @Test
    void after() {
        List<Apple> greenApplesResult = filterApples(List.of(APPLE), Apple::isGreenApple);
        List<Apple> heavyApplesResult = filterApples(List.of(APPLE), Apple::isHeavyApple);

        assertThat(greenApplesResult).containsExactly(APPLE);
        assertThat(heavyApplesResult).isEmpty();
    }
}