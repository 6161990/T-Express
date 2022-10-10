package com.yoon.TExpress.modernJavaInAction.four;

import lombok.Value;

@Value
public class Trader {
     String name;
     String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Trader:" + this.name +
                ", in " + this.city;
    }
}
