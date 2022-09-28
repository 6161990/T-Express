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
        List<Apple> redAppleResult = AppleFilter.filterApples(List.of(APPLE), Color.RED);

        assertThat(redAppleResult).containsExactly();
    }

    @Test
    void after2_가능한_모든속성으로_필터링한다() {
        List<Apple> colorAppleResult = AppleFilter.filterApples(List.of(APPLE), Color.GREEN, 0, true);
        List<Apple> weightAppleResult = AppleFilter.filterApples(List.of(APPLE), null, 170, false);

        assertThat(colorAppleResult).containsExactly(APPLE);
        assertThat(weightAppleResult).containsExactly();
    }


}
