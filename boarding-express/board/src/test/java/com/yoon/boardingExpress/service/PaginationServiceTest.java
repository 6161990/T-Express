package com.yoon.boardingExpress.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class) // classes : 스프링 부트 테스트의 설정값을 불러들이는 것, 특정 빈만 지정하여 무게를 줄일 수 있다. default 는 애플리케이션 전체 설정을 가져온다.
class PaginationServiceTest {

    @Autowired
    private PaginationService sut;

    @DisplayName("현재 페이지 넘버와 총 페이지 수로 페이지 바 리스트를 가져온다")
    @MethodSource
    @ParameterizedTest(name = "[{index}] 현재 페이지={0}, 총 페이지={1} => {2}")
    void getPaginationBar(int currentPageNumber, int totalPageNumber, List<Integer> expected) {
        List<Integer> actual = sut.getPaginationBarNumbers(currentPageNumber, totalPageNumber);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> getPaginationBar(){
        return Stream.of(
                arguments(0, 13, List.of(0, 1, 2, 3, 4)),
                arguments(1, 13, List.of(0, 1, 2, 3, 4)),
                arguments(2, 13, List.of(0, 1, 2, 3, 4)),
                arguments(3, 13, List.of(1, 2, 3, 4, 5)),
                arguments(4, 13, List.of(2, 3, 4, 5, 6)),
                arguments(5, 13, List.of(3, 4, 5, 6, 7)),
                arguments(6, 13, List.of(4, 5, 6, 7, 8)),
                arguments(10, 13, List.of(8, 9, 10, 11, 12)),
                arguments(11, 13, List.of(9, 10, 11, 12)),
                arguments(12, 13, List.of(10, 11, 12))
        );
    }

    @DisplayName("스펙의 명세를 드러내기 위한 테스트")
    @Test
    void 정의된_BarLength_를_알려준다() {

        int actual = sut.getBarLength();

        assertThat(actual).isEqualTo(5);
    }
}