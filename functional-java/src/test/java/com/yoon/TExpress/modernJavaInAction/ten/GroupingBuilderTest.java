package com.yoon.TExpress.modernJavaInAction.ten;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static com.yoon.TExpress.modernJavaInAction.ten.GroupingBuilder.groupOn;

class GroupingBuilderTest {

    @Test
    void step1() {
        Collector<? super Car, ?, Map<String, Map<String, List<Car>>>> mapCollector
                = groupOn(Car::getColor).after(Car::getBrand).get();
        // 그룹화 함수를 구현해야 하므로 유틸리티 사용 코드가 직관적이지 않다.
    }
}