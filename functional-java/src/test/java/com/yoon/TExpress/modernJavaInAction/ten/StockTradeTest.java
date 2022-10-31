package com.yoon.TExpress.modernJavaInAction.ten;

import org.junit.jupiter.api.Test;

class StockTradeTest {

    @Test
    void step1_장황한_주문만들기() {
        Order order = new Order();
        order.setCustomer("BigBank");

        Trade trade1 = new Trade();
        trade1.setTradeType(TradeType.BUY);

        Stock stock1 = new Stock();
        stock1.setSymbol("IBM");
        stock1.setMarket("NYSE");

        trade1.setStock(stock1);
        trade1.setPrice(125.00);
        trade1.setQuantity(80);
        order.addTrader(trade1);

        Trade trade2 = new Trade();
        trade2.setTradeType(TradeType.SELL);

        Stock stock2 = new Stock();
        stock2.setSymbol("GOOGLE");
        stock2.setMarket("NASDAQ");

        trade2.setStock(stock2);
        trade2.setPrice(375.00);
        trade2.setQuantity(50);
        order.addTrader(trade2);
    }

    @Test
    void step2_메서드체인() {
        Order order = forCustomer("BigBank")
                .buy(80)
                .stock("IBM")
                .on("NYSE")
                .at(125_00)

                .sell(50)
                .stock("GOOGLE")
                .on("NASDAQ")
                .at(375_00)
                .end();
    }
}
