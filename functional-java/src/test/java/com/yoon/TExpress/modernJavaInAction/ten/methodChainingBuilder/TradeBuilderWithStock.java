package com.yoon.TExpress.modernJavaInAction.ten.methodChainingBuilder;

import com.yoon.TExpress.modernJavaInAction.ten.Trade;
import com.yoon.TExpress.modernJavaInAction.ten.methodChainingBuilder.MethodChainingOrderBuilder;

public class TradeBuilderWithStock {

    private final MethodChainingOrderBuilder builder;
    private final Trade trade;

    public TradeBuilderWithStock(MethodChainingOrderBuilder builder, Trade trade) {
        this.builder = builder;
        this.trade = trade;
    }

    public MethodChainingOrderBuilder at(int price) {
        trade.setPrice(price);
        return builder.addTrade(trade);
    }
}
