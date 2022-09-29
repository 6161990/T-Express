package com.yoon.TExpress.modernJavaInAction.two;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AppleFilterTest {

    private static final Apple APPLE = new Apple(140, Color.GREEN);
    private static final Apple APPLE2 = new Apple(170, Color.RED);
    private static final Apple APPLE3 = new Apple(170, Color.GREEN);

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

    @Test
    void after3_동작_파라미터화한다() {
        AppleGreenColorPredicate appleGreenColorPredicate =  new AppleGreenColorPredicate();
        AppleHeavyWeightPredicate appleHeavyWeightPredicate =  new AppleHeavyWeightPredicate();

        List<Apple> colorAppleResult = AppleFilter.filterApples(List.of(APPLE), appleGreenColorPredicate);
        List<Apple> weightAppleResult = AppleFilter.filterApples(List.of(APPLE2), appleHeavyWeightPredicate);

        assertThat(colorAppleResult).containsExactly(APPLE);
        assertThat(weightAppleResult).containsExactly(APPLE2);
    }

    @Test
    void after4_apple_속성과_관련한_모든_변화에_대응할_수_있는_유연한_코드() {
        AppleGreenAndHeavyWeightPredicate andPredicate = new AppleGreenAndHeavyWeightPredicate();

        List<Apple> colorAppleResult = AppleFilter.filterApples(List.of(APPLE3), andPredicate);

        assertThat(colorAppleResult).containsExactly(APPLE3);
    }

    @Test
    void Q() {
        AppleSimpleFormatter simpleFormatter = new AppleSimpleFormatter();
        AppleFancyFormatter fancyFormatter = new AppleFancyFormatter();

        List<String> simples = AppleFilter.filterApples(List.of(APPLE), simpleFormatter);
        List<String> fancies = AppleFilter.filterApples(List.of(APPLE2), fancyFormatter);

        assertThat(simples).containsExactly("An apple of" + APPLE.getWeight() + "g");
        assertThat(fancies).containsExactly("This " + APPLE2.getColor()  + " Heavy Apple");
    }
}
