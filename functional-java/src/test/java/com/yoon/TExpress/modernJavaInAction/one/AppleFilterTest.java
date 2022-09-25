package com.yoon.TExpress.modernJavaInAction.one;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.yoon.TExpress.modernJavaInAction.one.AppleFilter.*;
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

    @Test
    void after2_andFilter() {
        Apple APPLE2 = new Apple(160, "GREEN");
        List<Apple> heavyAndGreenResult = andFilterDefaultHeavy(List.of(APPLE, APPLE2), Apple::isGreenApple);

        assertThat(heavyAndGreenResult).containsExactly(APPLE2);
    }

    @Test
    void after3_orFilter() {
        List<Apple> heavyAndGreenResult = orFilterDefaultHeavy(List.of(APPLE), Apple::isGreenApple);

        assertThat(heavyAndGreenResult).containsExactly(APPLE);
    }

    @Test
    void after4_negativeFilter() {
        Apple APPLE2 = new Apple(120, "RED");
        List<Apple> heavyAndGreenResult = negativeFilterDefaultHeavy(List.of(APPLE2), Apple::isGreenApple);

        assertThat(heavyAndGreenResult).containsExactly(APPLE2);
    }

    @Test
    void after_withLambdas() {
        List<Apple> greenApplesResult = filterApples(List.of(APPLE), (Apple a) -> "GREEN".equals(a.getColor()));
        List<Apple> heavyApplesResult = filterApples(List.of(APPLE), (Apple a) -> a.getWeight() > 150);

        assertThat(greenApplesResult).containsExactly(APPLE);
        assertThat(heavyApplesResult).isEmpty();
    }
}