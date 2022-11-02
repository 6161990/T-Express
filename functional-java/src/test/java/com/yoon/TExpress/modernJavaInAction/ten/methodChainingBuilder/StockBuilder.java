package com.yoon.TExpress.modernJavaInAction.ten.methodChainingBuilder;

import com.yoon.TExpress.modernJavaInAction.ten.Stock;
import com.yoon.TExpress.modernJavaInAction.ten.Trade;

public class StockBuilder {
    private final MethodChainingOrderBuilder builder;
    private final Trade trade;
    private final Stock stock = new Stock();

    public StockBuilder(MethodChainingOrderBuilder builder, Trade trade, String symbol) {
        this.builder = builder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }


    public TradeBuilderWithStock on(String market) {
        stock.setMarket(market);
        trade.setStock(stock);
        return new TradeBuilderWithStock(builder, trade);
    }
}
