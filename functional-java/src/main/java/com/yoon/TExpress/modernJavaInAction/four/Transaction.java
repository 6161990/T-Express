package com.yoon.TExpress.modernJavaInAction.four;

import lombok.Value;

@Value
public class Transaction {
    Trader trader;
    int year;
    int value;
    String referenceCode;

    public Transaction(Trader trader, int year, int value, String referenceCode) {
        this.trader = trader;
        this.year = year;
        this.value = value;
        this.referenceCode = referenceCode;
    }

    @Override
    public String toString() {
        return "{" + this.trader +
                ", year:" + this.year +
                ", value:" + this.value +
                '}';
    }
}
