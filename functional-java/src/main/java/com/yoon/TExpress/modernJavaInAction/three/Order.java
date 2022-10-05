package com.yoon.TExpress.modernJavaInAction.three;

public class Order {

    private Integer amount;
    private Integer count;
    private Integer discountPrice;

    public Order(Integer amount, Integer count, Integer discountPrice) {
        this.amount = amount;
        this.count = count;
        this.discountPrice = discountPrice;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }
}
