package com.yoon.TExpress.modernJavaInAction.two;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.yoon.TExpress.modernJavaInAction.two.Color.GREEN;
import static org.assertj.core.api.Assertions.assertThat;

public class AppleSortTest {

    private static final Apple APPLE = new Apple(110, GREEN);
    private static final Apple APPLE2 = new Apple(120, Color.RED);
    private static final Apple APPLE3 = new Apple(160, GREEN);

    @Test
    void apple_정렬() {
        List<Apple> arrayList = new ArrayList<>();
        arrayList.add(APPLE);
        arrayList.add(APPLE2);
        arrayList.add(APPLE3);

        List<Apple> apples = AppleSort.sortApples(arrayList, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.compareTo(o2);
            }
        });

        assertThat(apples).containsExactly(APPLE, APPLE2, APPLE3 );
    }
}
