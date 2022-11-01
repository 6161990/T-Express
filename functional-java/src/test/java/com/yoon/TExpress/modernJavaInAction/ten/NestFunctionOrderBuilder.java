package com.yoon.TExpress.modernJavaInAction.ten;

import java.util.stream.Stream;

public class NestFunctionOrderBuilder {

    public static Order order(String customer, Trade...trades){
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(trades).forEach(order::addTrade);
        return order;
    }

    public static Trade buy(int quantity, Stock stock, double price){
        return buildTrade(quantity, stock, price, TradeType.BUY);
    }

    public static Trade sell(int quantity, Stock stock, double price){
        return buildTrade(quantity, stock, price, TradeType.SELL);
    }

    private static Trade buildTrade(int quantity, Stock stock, double price, TradeType tradeType) {
        Trade trade = new Trade();
        trade.setQuantity(quantity);
        trade.setStock(stock);
        trade.setPrice(price);
        trade.setTradeType(tradeType);
        return trade;
    }

    public static Stock stock(String symbol, String market){
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setMarket(market);
        return stock;
    }

    public static double at(double price){
        return price;
    }

    public static String on(String market){
        return market;
    }


}
