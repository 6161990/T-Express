package com.yoon.TExpress.modernJavaInAction.two;

import java.util.Objects;

public class Apple implements Fruit {

    private Integer weight;
    private String weightString;
    private Color color;

    public Apple() {

    }

    public Apple(Integer weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Apple(String weightString, Color color) {
        this.weightString = weightString;
        this.color = color;
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public int compareTo(Apple a1) {
        return a1.getWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apple apple = (Apple) o;
        return weight == apple.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color=" + color +
                '}';
    }
}