package com.yoon.TExpress.javaFunctional.practice;

import lombok.Value;

@Value
public class AllocatableItem {
    Object value;
    long price;

    public static AllocatableItem of(Object type, long price) {
        return new AllocatableItem(type, price);
    }

    private AllocatableItem(Object value, long price) {
        this.value = value;
        this.price = price;
    }

}
