package com.yoon.TExpress.modernJavaInAction.eight;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactory {

    @Test
    void step1_list_factory_로_생성하면_변경_불가능하다() {
        List<String> friends = List.of("냥냥", "멍멍", "음메");

        System.out.println(friends);
        // friends.add("꿀꿀"); java.lang.UnsupportedOperationException
    }

    @Test
    void step2_set_factory_로_생성하면_변경_불가능하다() {
        Set<String> friends = Set.of("냥냥", "멍멍", "음메");

        System.out.println(friends);
        // friends.add("꿀꿀"); java.lang.UnsupportedOperationException
    }

    @Test
    void step3_map_factory_로_생성하면_변경_불가능하다() {
        Map<String, Integer> friends = Map.of("냥냥", 1, "멍멍", 4, "음메", 7);

        System.out.println(friends);
       // friends.put("꿀꿀", 8); java.lang.UnsupportedOperationException
    }


    @Test
    void step4_mapOfEntries_로_생성하면_변경_불가능하다_with_entry() { // 열개 이상의 쌍 (map) 을 만들 때에는 가변 인수로 구현된 ofEntries 이용하는 것이 좋음 with entry()
        Map<String, Integer> friends = Map.ofEntries(Map.entry("냥냥", 1), Map.entry("멍멍", 4), Map.entry("음메", 7));

        System.out.println(friends);
        // friends.put("꿀꿀", 8); java.lang.UnsupportedOperationException
    }


}
