package com.yoon.TExpress.modernJavaInAction.two;

public class AppleFancyFormatter implements AppleFormatter{
    @Override
    public String accept(Apple e) {
        String selected = e.getWeight() > 150 ? " Heavy" : " Light";
        return "This " + e.getColor() + selected + " Apple";
    }
}
