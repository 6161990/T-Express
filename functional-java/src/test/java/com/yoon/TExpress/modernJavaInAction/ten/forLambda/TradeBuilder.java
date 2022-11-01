package com.yoon.TExpress.modernJavaInAction.ten.forLambda;

import com.yoon.TExpress.modernJavaInAction.ten.Trade;

import java.util.function.Consumer;

public class TradeBuilder {
    protected Trade trade = new Trade();

    public void quantity(int quantity){
        trade.setQuantity(quantity);
    }

    public void price(double price){
        trade.setPrice(price);
    }

    public void stock(Consumer<StockBuilder> consumer){
        StockBuilder builder = new StockBuilder();
        consumer.accept(builder);
        trade.setStock(builder.stock);
    }
}
