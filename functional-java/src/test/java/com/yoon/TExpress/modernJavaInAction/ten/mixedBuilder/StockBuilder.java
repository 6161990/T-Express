package com.yoon.TExpress.modernJavaInAction.ten.mixedBuilder;

import com.yoon.TExpress.modernJavaInAction.ten.Stock;
import com.yoon.TExpress.modernJavaInAction.ten.Trade;

public class StockBuilder {
    private final TradeBuilder builder;
    private final Trade trade;
    private final Stock stock = new Stock();

    public StockBuilder(TradeBuilder tradeBuilder, Trade trade, String symbol) {
        this.builder = tradeBuilder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilder on(String market){
        stock.setMarket(market);
        trade.setStock(stock);
        return builder;
    }
}
