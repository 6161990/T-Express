package com.yoon.TExpress.modernJavaInAction.three;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {

    @Test
    void name() {
        forEach(Arrays.asList(1, 2, 3, 4, 5), integer -> System.out.println(integer));
    }

    public <T> void forEach(List<T> list, Consumer<T> c){
        for(T t : list){
            c.accept(t);
        }
    }
}
