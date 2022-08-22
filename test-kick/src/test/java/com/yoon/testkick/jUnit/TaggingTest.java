package com.yoon.testkick.jUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaggingTest {

    @DisplayName("fast")
    @Tag("fast")
    @Test
    void fast_tag_test() {
        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @DisplayName("slow")
    @Tag("slow")
    @Test
    void slow_tag_test() {
        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @FastTest
    void fast_tag_test_with_annotation() {
        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }

    @SlowTest
    void slow_tag_test_with_annotation() {
        System.out.println("EXECUTE");
        BasicClass basicClass = new BasicClass();
        assertEquals(StudyStatus.DRAFT, basicClass.getStatus(), "Basic 을 처음만들면 상태값이 DRAFT 여야한다.");
    }
}