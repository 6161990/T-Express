package com.yoon.TExpress.modernJavaInAction.two;

import java.util.Objects;

public class Orange implements Fruit{

    private int weight;

    public Orange(Integer weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orange orange = (Orange) o;
        return weight == orange.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }
}
