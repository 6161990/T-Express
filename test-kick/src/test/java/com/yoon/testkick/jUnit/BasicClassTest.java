package com.yoon.testkick.jUnit;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
/** 각 테스트의 underscore 를 빈칸으로 replace.*/
class BasicClassTest {

    @Test
    @DisplayName(" 🤢 ")
    void basic_test_1() {
        BasicClass basicClass = new BasicClass();

        System.out.println("Basic basic_test_1 ");
        assertNotNull(basicClass);
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
        // 기대값, 실제값, 테스트 실패 시 출력할 메세지 순이 정석
    }

    @Test
    void basic_test_2() {
        BasicClass basicClass = new BasicClass();

        System.out.println("Basic basic_test_2 ");
        assertNotNull(basicClass);
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), new Supplier<String>() {
            @Override
            public String get() {
                return "Basic 을 처음만들면 상태값이 DRAFT 여야한다.";
            }
        });

        /** basic_test_1, 2의 세번째 인자값 구현 차이점
         * 1 처럼 message 로 바로 "" 주었을 때는, 실패하던 성공하던 연산하지만,
         * 2 처럼 구현했을 때는, 실패했을 때에만 연산한다. -> 문자 연산의 비용이 든다면 2 를 택.
         * */
    }

    @Test
    void basic_test_2_1() {
        BasicClass basicClass = new BasicClass(7);

        assertAll(
                () -> assertNotNull(basicClass),
                () -> assertEquals(StudyStatus.DRAFT, basicClass.getStatus(),
                        ()-> "Basic 을 처음만들면 상태값이 DRAFT 여야한다."),
                ()-> assertTrue(basicClass.getLimit() > 0, "Basic 제한 값이 0 이상이다.")
        );

        /** assertAll :
         *  모든 assert 문을 한꺼번에 검증해서 어떤 assert 가 실패했는지 전부 알려준다.
         * */
    }

    @Test
    @Disabled /** 테스트 실행하지 않는다.*/
    void basic_test_3() {
        BasicClass basicClass = new BasicClass();

        assertNotNull(basicClass);
        System.out.println("Basic basic_test_3 disabled ");
    }

    @Test
    void basic_test_4() {
        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class, () -> new BasicClass(-10));

        assertEquals("limit은 0보다 커야한다", exception.getMessage());
    }

    @Test
    void basic_test_5() {
        assertTimeout(Duration.ofMillis(100), ()-> {
            new BasicClass(10);
            Thread.sleep(300);
        });
        /** 테스트를 다 실행한 후, 100millis 를 초과하면 테스트가 실패한다.
         **/
    }

    @Test
    void basic_test_6() {
        assertTimeoutPreemptively(Duration.ofMillis(100), ()-> {
            new BasicClass(10);
            Thread.sleep(300);
        });
        /** assertTimeoutPreemptively 테스트를 다 실행하지않고, 100millis 를 초과하면 테스트가 실패한다.
         *  단 , 트랜잭션 처럼 thread local 에서 실행되는 경우는, 예외상황(테스트 성공)이 발생할 수 있다.
         **/
    }

    /** 해당 테스트 클래스가 실행될 때, 단 한번 실행되는 메소드.
     반드시 static , non-private , no return */
    @BeforeAll
    static void beforeAll() {
        System.out.println("Basic beforeAll ");
    }

    /** 해당 테스트 클래스가 실행된 이후, 단 한번 실행되는 메소드.
     반드시 static , non-private , no return */
    @AfterAll
    static void afterAll() {
        System.out.println("Basic afterAll ");
    }

    /** 각 테스트 메소드가 실행되기 전, 한번씩 실행되는 메소드.
     반드시 non-private , no return */
    @BeforeEach
    void setUp() {
        System.out.println("Basic setUp ");
    }


    /** 각 테스트 메소드가 실행된 후, 한번씩 실행되는 메소드.
     반드시 non-private , no return */
    @AfterEach
    void tearDown() {
        System.out.println("Basic tearDown");
    }
}