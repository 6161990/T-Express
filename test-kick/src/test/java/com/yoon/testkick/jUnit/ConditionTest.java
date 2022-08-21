package com.yoon.testkick.jUnit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.*;

class ConditionTest {

    @DisplayName("assumeTrue 일 때만 아래 코드를 실행한다.")
    @Test
    void assumeTrue_test() {
        String user = System.getenv("USER");
        System.out.println(user);
        assumeTrue("6161990src".equals(user));

        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }


    @DisplayName("assumeTrue 일 때만 아래 코드를 실행한다.2")
    @Test
    void assumeFalse_test() {
        String user = System.getenv("USER");
        System.out.println(user);
        assumeTrue("6161990srcXXXX".equals(user));

        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @DisplayName("assumingThat 이 true 일 때만 뒤 코드를 실행한다.")
    @Test
    void assumingThat_test() {
        String user = System.getenv("USER");

        assumingThat("6161990src".equals(user), () ->{
            System.out.println("EXECUTE");
            BasicClass basicClass = new BasicClass();
            assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
        });
    }

    @DisplayName("assumingThat 이 true 일 때만 뒤 코드를 실행한다2.")
    @Test
    void assumingThat_test2() {
        String user = System.getenv("USER");

        assumingThat("6161990srcXXX".equals(user), () ->{
            System.out.println("EXECUTE");
            BasicClass basicClass = new BasicClass();
            assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
        });
    }

}
