package com.yoon.TExpress.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExampleTest {

    List<OnlineClass> springClasses = new ArrayList<>();
    List<OnlineClass> javaClasses = new ArrayList<>();
    List<List<OnlineClass>> events = new ArrayList<>();

    @DisplayName("spring 으로 시작하는 수업")
    @Test
    void exam1() {
        getSpringClasses().stream().filter(i -> i.getTitle().startsWith("spring")).forEach(i-> System.out.println(i.getTitle()));
    }

    @DisplayName("close 되지 않은 수업")
    @Test
    void exam2() {
        // getSpringClasses().stream().filter(i-> !i.isClosed()).forEach(i-> System.out.println(i.getTitle()));
        System.out.println("==Refactoring to 29 lines==");
        getSpringClasses().stream().filter(Predicate.not(OnlineClass::isClosed)).forEach(i-> System.out.println(i.getTitle())); // 임의 객체 :: 메소드 레퍼런스 활용

        System.out.println("--------------------");
        System.out.println("--------------------");
        getJavaClasses().stream().filter(i-> !i.isClosed()).forEach(i-> System.out.println(i.getTitle()));
        System.out.println("--------------------");
    }

    @DisplayName("수업 이름만 모아서 스트림 만들기")
    @Test
    void exam3() {
        // map 은 들어올 때는 OnlineClass 가 들어오는데 return 은 String
        getSpringClasses().stream().map(OnlineClass::getTitle).forEach(System.out::println); // :: 메소드 레퍼런스 활용
    }

    @DisplayName("두 수업 목록에 들어있는 모든 수업 아이디 출력")
    @Test
    void exam4() {
        List<OnlineClass> springClasses = getSpringClasses();
        List<OnlineClass> javaClasses = getJavaClasses();
        events.add(springClasses);
        events.add(javaClasses);

        events.stream().flatMap(Collection::stream).forEach(oc-> System.out.println(oc.getTitle()));
    }

    @DisplayName("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만")
    @Test
    void exam5() {
        Stream.iterate(10, i-> i+1).skip(10).limit(10).forEach(System.out::println);
    }

    @DisplayName("자바 수업 중에 Test가 들어있는 수업이 있는지 확인")
    @Test
    void exam6() {
        boolean actual = getJavaClasses().stream().anyMatch(oc -> oc.getTitle().contains("Test"));

        Assertions.assertTrue(actual);
    }

    @DisplayName("스프링 수업 중에 제목에 spring이 들어간 것만 모아서 List로 만들기")
    @Test
    void exam7() {
        List<String> actual = getSpringClasses().stream().filter(oc -> oc.getTitle().contains("spring")).map(OnlineClass::getTitle).collect(Collectors.toList());
        actual.forEach(System.out::println);
    }


    public List<OnlineClass> getSpringClasses(){
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));
        return springClasses;
    }

    public List<OnlineClass> getJavaClasses(){
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));
        return javaClasses;
    }


}
