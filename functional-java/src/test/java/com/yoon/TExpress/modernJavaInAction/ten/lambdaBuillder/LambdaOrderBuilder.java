package com.yoon.TExpress.modernJavaInAction.ten.lambdaBuillder;

import com.yoon.TExpress.modernJavaInAction.ten.Order;
import com.yoon.TExpress.modernJavaInAction.ten.TradeType;

import java.util.function.Consumer;

public class LambdaOrderBuilder {

    private Order order = new Order(); // 빌더로 주문을 감싼다

    public static Order orderForLambda(Consumer<LambdaOrderBuilder> consumer){
        LambdaOrderBuilder builder = new LambdaOrderBuilder();
        consumer.accept(builder); //주문 빌더로 전달된 람다 표현식 실행
        return builder.order; // OrderBuilder 의 Consumer 를 실행해 만들어진 주문을 반환
    }

    public void forCustomer(String customer){
        order.setCustomer(customer); // 주문을 요청한 고객 설정
    }

    public void buy(Consumer<TradeBuilder> consumer){
        trade(consumer, TradeType.BUY); // 주식 매수 주문을 만들도록 TradeBuilder 소비
    }

    public void sell(Consumer<TradeBuilder> consumer){
        trade(consumer, TradeType.SELL); // 주식 매도 주문을 만들도록 TradeBuilder 소비
    }

    private void trade(Consumer<TradeBuilder> consumer, TradeType tradeType) {
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setTradeType(tradeType);
        consumer.accept(builder); // TradeBuilder 로 전달할 람다 표현식 실행
        order.addTrade(builder.trade); // TradeBuilder 의 Consumer 를 실행해 만든 거래를 주문에 추가
    }

}
