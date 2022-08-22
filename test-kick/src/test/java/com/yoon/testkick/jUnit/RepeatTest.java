package com.yoon.testkick.jUnit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepeatTest {

    @RepeatedTest(5)
    void repeat_test(RepetitionInfo repetitionInfo) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @DisplayName("반복 테스트")
    @RepeatedTest(value = 5, name = "{displayName}, {currentRepetition} / {totalRepetitions}")
    void repeat_test2(RepetitionInfo repetitionInfo) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"오늘도", "뚠뚠", "나는", "뚠뚠", "개미"})
    void repeat_test3(String message) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(message);
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @DisplayName("반복 테스트")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"오늘도", "뚠뚠", "나는", "뚠뚠", "개미"})
    void repeat_test4(String message) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(message);
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }
}
