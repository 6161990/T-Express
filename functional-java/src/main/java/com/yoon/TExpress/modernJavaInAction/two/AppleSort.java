package com.yoon.TExpress.modernJavaInAction.two;

import java.util.Comparator;
import java.util.List;

public class AppleSort {
    public static List<Apple> sortApples(List<Apple> inventory, Comparator<Apple> appleComparator){
        inventory.sort(appleComparator);
        return inventory;
    }
}
