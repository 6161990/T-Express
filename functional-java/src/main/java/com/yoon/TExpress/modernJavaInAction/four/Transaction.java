package com.yoon.TExpress.modernJavaInAction.four;

import lombok.Value;

@Value
public class Transaction {
    Trader trader;
    int year;
    int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + this.trader +
                ", year:" + this.year +
                ", value:" + this.value +
                '}';
    }
}
