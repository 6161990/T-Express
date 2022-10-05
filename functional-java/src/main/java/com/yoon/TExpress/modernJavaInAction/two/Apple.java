package com.yoon.TExpress.modernJavaInAction.two;

import java.util.Objects;

public class Apple implements Comparable<Apple> {

    private int weight;
    private String weightString;
    private Color color;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Apple(String weightString, Color color) {
        this.weightString = weightString;
        this.color = color;
    }

    public Apple() {

    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getWeightString() {
        return weightString;
    }

    public void setWeightString(String weightString) {
        this.weightString = weightString;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    @Override
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
}