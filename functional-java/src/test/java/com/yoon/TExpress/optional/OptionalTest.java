package com.yoon.TExpress.optional;

import com.yoon.TExpress.stream.OnlineClass;
import com.yoon.TExpress.stream.Progress;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class OptionalTest {

    @Test
    void optional_with_filter() {
        List<OnlineClass> springClasses = getSpringClasses();
        Optional<OnlineClass> spring = springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        Optional<OnlineClass> jpa = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();

        assertEquals(spring.get().getId(), 1);
        assertFalse(jpa.isPresent());
    }

    @Test
    void with_NoSuchElementException() {
        List<OnlineClass> springClasses = getSpringClasses();

        Optional<OnlineClass> jpa = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa")).findFirst();

        assertThrows(NoSuchElementException.class, jpa::get);
    }

    @Test
    void with_NoSuchElementException_2() {
        List<OnlineClass> springClasses = getSpringClasses();

        Optional<OnlineClass> jpa = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa")).findFirst();

        if(jpa.isPresent()){ /** exception이 나기 때문에 값이 있는지 확인하고 꺼내와야하는데, 해당 방법은 우아하지 않다 */
            OnlineClass onlineClass = jpa.get();
        }
    }

    @Test
    void with_NoSuchElementException_2의_개선() {
        List<OnlineClass> springClasses = getSpringClasses();

        Optional<OnlineClass> jpa = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa")).findFirst();

        jpa.ifPresent(oc-> {
            System.out.println(oc.getTitle());
        });
    }

    @Test
    void orElse() {
        List<OnlineClass> springClasses = getSpringClasses();

        Optional<OnlineClass> dong = springClasses.stream().filter(oc -> oc.getTitle().startsWith("dong")).findFirst();
        Optional<OnlineClass> spring = springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring")).findFirst();

        OnlineClass onlineClass = dong.orElse(createNewClass());
        OnlineClass onlineClass2 = spring.orElse(createNewClass());

        assertEquals(onlineClass.getId(),10);
        assertEquals(onlineClass2.getId(),1); // 있는데 새로 생성됨
        /** 하지만, 이 방법은 있어도 생성하고 없어도 생성한다 .. 뭔가 좀찜찜*/
    }

    @Test
    void orElseGet() {
        List<OnlineClass> springClasses = getSpringClasses();

        Optional<OnlineClass> dong = springClasses.stream().filter(oc -> oc.getTitle().startsWith("dong")).findFirst();

        OnlineClass onlineClass = dong.orElseGet(OptionalTest::createNewClass);

        assertEquals(onlineClass.getId(),10);
        /** 없을 때만 생성한다*/
    }

    @Test
    void orElseThrow() {
        List<OnlineClass> springClasses = getSpringClasses();

        Optional<OnlineClass> dong = springClasses.stream().filter(oc -> oc.getTitle().startsWith("dong")).findFirst();

        // OnlineClass onlineClass = dong.orElseThrow(IllegalArgumentException::new);
        assertThatThrownBy(dong::orElseThrow).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void when_notPresent_wrapper_stream() {
        List<OnlineClass> springClasses = getSpringClasses();

        Optional<OnlineClass> dong = springClasses.stream().filter(oc -> oc.getTitle().startsWith("dong")).findFirst();
        long actual = dong.stream().mapToLong(OnlineClass::getId).sum();

        assertThat(actual).isEqualTo(0);
    }

    @Test
    void optional_with_filter_map() {
        List<OnlineClass> springClasses = getSpringClasses();

        Optional<OnlineClass> jpa = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();

        Optional<Integer> integer = jpa.map(OnlineClass::getId);
        assertFalse(integer.isPresent());
    }

    @Test
    void optional_with_two_optional() {
        List<OnlineClass> springClasses = getSpringClasses();

        Optional<OnlineClass> jpa = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();

        Optional<Optional<Progress>> progress = jpa.map(OnlineClass::getProgress);

        assertThatThrownBy(progress::orElseThrow).isInstanceOf(NoSuchElementException.class);

        /** Optional이 또 감싸져 있는 경우, 양파 까듯이 까서 꺼내와야한다 */
    }

    @Test
    void optional_with_filter_flatMap() {
        List<OnlineClass> springClasses = getSpringClasses();

        Optional<OnlineClass> jpa = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();

        Optional<Progress> progress = jpa.flatMap(OnlineClass::getProgress);
        /** flatMap 을 사용하면된다
         * Stream 의 flatMap 과는 다르다. 그 때는 input이 하나인데, output이 여러개인 경우 사용한다.
         * */
    }

    private static OnlineClass createNewClass() {
        System.out.println("creating");
        return new OnlineClass(10, "New", false);
    }

    public List<OnlineClass> getSpringClasses(){
    List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));
        return springClasses;
    }
}
