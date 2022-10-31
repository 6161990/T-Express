package com.yoon.TExpress.modernJavaInAction.ten;

import com.yoon.TExpress.javaFunctional.FunctionIsFunction;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

import static java.util.stream.Collectors.groupingBy;

// 그룹화 컬렉터 빌더
public class GroupingBuilder<T, D, K> {
    private final Collector<? super T, ?, Map<K,D>> collector;

    public GroupingBuilder(Collector<? super T, ?, Map<K, D>> collector) {
        this.collector = collector;
    }

    public Collector<? super T, ?, Map<K, D>> get() {
        return collector;
    }

    public <J> GroupingBuilder<T, Map<K, D>, J> after(Function<? super T, ? extends J> classifier){
        return new GroupingBuilder<>(groupingBy(classifier, collector));
    }

    public static <T, D, K> GroupingBuilder<T, List<T>, K> groupOn(Function<? super T, ? extends K> classifier){
        return new GroupingBuilder<>(groupingBy(classifier));
    }

}