package com.yoon.TExpress.modernJavaInAction.three;

import com.yoon.TExpress.modernJavaInAction.two.Apple;
import com.yoon.TExpress.modernJavaInAction.two.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
}
