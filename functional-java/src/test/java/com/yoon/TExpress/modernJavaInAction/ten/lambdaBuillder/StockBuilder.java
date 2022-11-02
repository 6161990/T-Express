package com.yoon.TExpress.modernJavaInAction.ten.lambdaBuillder;

import com.yoon.TExpress.modernJavaInAction.ten.Stock;

public class StockBuilder {
    Stock stock = new Stock();

    public void symbol(String symbol){
        stock.setSymbol(symbol);
    }

    public void market(String market){
        stock.setMarket(market);
    }
}
