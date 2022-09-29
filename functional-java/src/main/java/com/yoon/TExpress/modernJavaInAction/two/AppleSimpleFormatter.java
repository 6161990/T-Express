package com.yoon.TExpress.modernJavaInAction.two;

public class AppleSimpleFormatter implements AppleFormatter{
    @Override
    public String accept(Apple e) {
        return "An apple of" + e.getWeight() + "g";
    }
}
