package com.yoon.testkick.jUnit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

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

    @ParameterizedTest(name = "{index} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void repeat_test9(@ConvertWith(BasicClassConverter.class) BasicClass basicClass) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(basicClass);
    }

    static class BasicClassConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(BasicClass.class, targetType, "Can only convert to basicClass");
            return new BasicClass(Integer.parseInt(source.toString()));
        }
    }

    @ParameterizedTest(name = "{index} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void repeat_test10(Integer limit, String name) {
        BasicClass basicClass = new BasicClass(limit, name);
        System.out.println("REPEAT-REPEAT!");
        System.out.println(basicClass);
    }

    @ParameterizedTest(name = "{index} message={0}")
    @CsvSource({"10, '자바 스터디!'", "20, 스프링 부트"})
    void repeat_test11(ArgumentsAccessor argumentsAccessor) {
        BasicClass basicClass = new BasicClass(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println("REPEAT-REPEAT!");
        System.out.println(basicClass);
    }

    @ParameterizedTest(name = "{index} message={0}")
    @CsvSource({"10, '자바 스터디!'", "20, 스프링 부트"})
    void repeat_test12(@AggregateWith(BasicAggregator.class) BasicClass basicClass) {
        System.out.println("REPEAT-REPEAT!");
        System.out.println(basicClass);
    }


    static class BasicAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new BasicClass(accessor.getInteger(0), accessor.getString(1));
        }
    }
}
