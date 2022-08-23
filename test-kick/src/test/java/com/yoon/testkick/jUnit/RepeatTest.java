package com.yoon.testkick.jUnit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepeatTest {

    @RepeatedTest(5)
    void repeat_test(RepetitionInfo repetitionInfo) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("반복 테스트")
    @RepeatedTest(value = 5, name = "{displayName}, {currentRepetition} / {totalRepetitions}")
    void repeat_test2(RepetitionInfo repetitionInfo) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @ParameterizedTest
    @ValueSource(strings = {"오늘도", "뚠뚠", "나는", "뚠뚠", "개미"})
    void repeat_test3(String message) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(message);
    }

    @DisplayName("반복 테스트")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"오늘도", "뚠뚠", "나는", "뚠뚠", "개미"})
    void repeat_test4(String message) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(message);
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    void repeat_test5(String message) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(message);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void repeat_test6(String message) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(message);
    }

    @ParameterizedTest(name = "{index} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void repeat_test7(Integer limit) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(limit);
    }

    @ParameterizedTest(name = "{index} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void repeat_test8(Integer limit) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(limit);
    }

}
