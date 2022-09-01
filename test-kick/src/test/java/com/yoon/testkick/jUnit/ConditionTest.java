package com.yoon.testkick.jUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

class ConditionTest {

    @DisplayName("assumeTrue 일 때만 테스트 코드를 실행한다.")
    @Test
    void assumeTrue_test() {
        String user = System.getenv("USER");
        System.out.println(user);
        assumeTrue("6161990src".equals(user));

        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }


    @DisplayName("assumeTrue 일 때만 테스트 코드를 실행한다-false 일 경우")
    @Test
    void assumeFalse_test() {
        String user = System.getenv("USER");
        System.out.println(user);
        assumeTrue("6161990srcXXXX".equals(user));

        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @DisplayName("assumingThat 이 true 일 때만 테스트 코드를 실행한다.")
    @Test
    void assumingThat_test() {
        String user = System.getenv("USER");

        assumingThat("6161990src".equals(user), () ->{
            System.out.println("EXECUTE");
            BasicClass basicClass = new BasicClass();
            assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
        });
    }

    @DisplayName("assumingThat 이 true 일 때만 테스트 코드를 실행한다-false 일 경우")
    @Test
    void assumingThat_test2() {
        String user = System.getenv("USER");

        assumingThat("6161990srcXXX".equals(user), () ->{
            System.out.println("EXECUTE");
            BasicClass basicClass = new BasicClass();
            assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
        });
    }

    @DisplayName("enabledOnOs 이 true 일 때만 테스트 실행한다.")
    @Test
    @EnabledOnOs(OS.MAC)
    void enabledOnOs_test1() {
        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @DisplayName("enabledOnOs 이 true 일 때만 테스트 실행한다.2")
    @Test
    @EnabledOnOs(OS.LINUX)
    void enabledOnOs_test2() {
        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @DisplayName("EnabledOnJre 이 true 일 때만 테스트 실행한다.")
    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void enabledOnJre_test() {
        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @DisplayName("EnabledOnJre 이 true 일 때만 테스트 실행한다.")
    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void enabledOnJre_test2() {
        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }


    @DisplayName("EnabledIfEnvironmentVariable 이 true 일 때만 테스트 실행한다.")
    @Test
    @EnabledIfEnvironmentVariable(named = "USER", matches = "6161990src")
    void EnabledIfEnvironmentVariable_test() {
        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @DisplayName("EnabledIfEnvironmentVariable 이 true 일 때만 테스트 실행한다.")
    @Test
    @EnabledIfEnvironmentVariable(named = "USER", matches = "6161990srcXXXXX")
    void EnabledIfEnvironmentVariable_test2() {
        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }


}
