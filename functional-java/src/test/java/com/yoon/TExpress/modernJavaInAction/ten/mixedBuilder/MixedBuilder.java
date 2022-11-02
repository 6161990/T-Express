package com.yoon.TExpress.modernJavaInAction.ten.mixedBuilder;

import com.yoon.TExpress.modernJavaInAction.ten.Order;
import com.yoon.TExpress.modernJavaInAction.ten.TradeType;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class MixedBuilder {
    public static Order forCustomer2(String customer, TradeBuilder... builders){
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(builders).forEach( b -> order.addTrade(b.trade));
        return order;
    }

    public static TradeBuilder buy(Consumer<TradeBuilder> consumer){
        return buildTrade(consumer, TradeType.BUY);
    }

    public static TradeBuilder sell(Consumer<TradeBuilder> consumer){
        return buildTrade(consumer, TradeType.SELL);
    }

    public static TradeBuilder buildTrade(Consumer<TradeBuilder> consumer, TradeType tradeType) {
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setTradeType(tradeType);
        consumer.accept(builder);
        return builder;
    }

}
