package com.yoon.TExpress.modernJavaInAction.three;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionTest {

    @Test
    void name() {
        List<Integer> map = map(Arrays.asList("Yoonji", "zzang"), new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        });

        System.out.println(map);
    }

    public <T,R> List<R> map(List<T> list, Function<T,R> f){
        List<R> result = new ArrayList<>();
        for (T t : list){
            result.add(f.apply(t));
        }
        return result;
    }
}
