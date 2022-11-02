package com.yoon.TExpress.modernJavaInAction.ten.methodChainingBuilder;

import com.yoon.TExpress.modernJavaInAction.ten.Trade;
import com.yoon.TExpress.modernJavaInAction.ten.TradeType;
import com.yoon.TExpress.modernJavaInAction.ten.methodChainingBuilder.MethodChainingOrderBuilder;
import com.yoon.TExpress.modernJavaInAction.ten.methodChainingBuilder.StockBuilder;

public class TradeBuilder {
    private final MethodChainingOrderBuilder builder;
    public final Trade trade = new Trade();

    public TradeBuilder(MethodChainingOrderBuilder builder, TradeType tradeType, int quantity){
        this.builder = builder;
        trade.setTradeType(tradeType);
        trade.setQuantity(quantity);
    }

    public StockBuilder stock(String symbol){
        return new StockBuilder(builder, trade, symbol);
    }

}
