package com.yoon.TExpress.modernJavaInAction.two;

public class Apple implements Comparable<Apple> {

    private int weight;
    private Color color;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
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
}