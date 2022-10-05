package com.yoon.TExpress.modernJavaInAction.three;

import com.yoon.TExpress.modernJavaInAction.two.Apple;
import com.yoon.TExpress.modernJavaInAction.two.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static org.assertj.core.api.Assertions.assertThat;

class AllStepTest {

    private List<Apple> inventory = new ArrayList<>();

    @BeforeEach
    void setUp() {
        inventory.add(new Apple(70, Color.RED));
        inventory.add(new Apple(70, Color.GREEN));
        inventory.add(new Apple(130, Color.GREEN));
        inventory.add(new Apple(200, Color.RED));
        inventory.add(new Apple(160, Color.GREEN));
        inventory.add(new Apple(90, Color.GREEN));
        inventory.add(new Apple(92, Color.GREEN));
    }

    @Test
    void Step1_동작파라미터화() {
        inventory.sort(new AppleComparator());

        System.out.println(inventory.stream().map(Apple::getWeight).collect(Collectors.toList()));
    }

    private class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight().compareTo(o2.getWeight());
        }
    }

    @Test
    void Step2_익명클래스사용() {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        System.out.println(inventory.stream().map(Apple::getWeight).collect(Collectors.toList()));
    }

    @Test
    void Step3_람다표현식사용_단계병() {
        inventory.sort((Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight()));

        inventory.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));

        inventory.sort(Comparator.comparing(apple -> apple.getWeight()));

        System.out.println(inventory.stream().map(Apple::getWeight).collect(Collectors.toList()));
    }

    @Test
    void Step4_메서드참조사용() {
        inventory.sort(comparing(Apple::getWeight));

        System.out.println(inventory.stream().map(Apple::getWeight).collect(Collectors.toList()));
    }

    @Test
    void Step5_Comparator_조합() {
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory.stream().map(Apple::getWeight).collect(Collectors.toList()));

        inventory.sort(comparing(Apple::getWeight).reversed()); /** 역정렬 */
        System.out.println(inventory.stream().map(Apple::getWeight).collect(Collectors.toList()));
    }

    @Test
    void Step5_Comparator_연결() {
        inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor)); /** 두 사과의 무게가 같으면 색깔별로 정렬*/

        System.out.println(inventory);
    }

    @Test
    void Step6_Function_조합_andThen_compose() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g); // andThen

        Integer actual = h.apply(1);
        assertThat(actual).isEqualTo(4);

        Function<Integer, Integer> h1 = f.compose(g); // compose
        Integer actual2 = h1.apply(1);
        assertThat(actual2).isEqualTo(3);
    }


}
