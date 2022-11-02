package com.yoon.TExpress.modernJavaInAction.ten;

import com.yoon.TExpress.modernJavaInAction.ten.mixedBuilder.MixedBuilder;
import org.junit.jupiter.api.Test;

import static com.yoon.TExpress.modernJavaInAction.ten.MethodChainingOrderBuilder.forCustomer;
import static com.yoon.TExpress.modernJavaInAction.ten.NestFunctionOrderBuilder.*;
import static com.yoon.TExpress.modernJavaInAction.ten.forLambda.LambdaOrderBuilder.orderForLambda;
import static com.yoon.TExpress.modernJavaInAction.ten.mixedBuilder.MixedBuilder.buy;
import static com.yoon.TExpress.modernJavaInAction.ten.mixedBuilder.MixedBuilder.forCustomer2;
import static com.yoon.TExpress.modernJavaInAction.ten.mixedBuilder.MixedBuilder.sell;

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
        order.addTrade(trade1);

        Trade trade2 = new Trade();
        trade2.setTradeType(TradeType.SELL);

        Stock stock2 = new Stock();
        stock2.setSymbol("GOOGLE");
        stock2.setMarket("NASDAQ");

        trade2.setStock(stock2);
        trade2.setPrice(375.00);
        trade2.setQuantity(50);
        order.addTrade(trade2);
    }

    @Test
    void step2_메서드체인() {
        // 사용자가 미리 지정된 절차에 따라 플루언트 API 메서드를 호출하도록 강제
        // 다음 거래를 설정하기 전에 기존 거래를 올바로 설정하게 된다.
        // 주문에 사용한 파라미터가 빌더 내부로 국한된다.
        // -> 정적 메서드 사용을 최소화하고 메서드 이름이 인수의 이름을 대신하도록 만듦
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

        System.out.println(order);
    }

    @Test
    void step3_중첩된함수() {
        // 도메인 계층 구조를 그대로 반영
        // 정적 메서드에 인수목록을 넘겨야함
        Order order = order("BigBank",
                            NestFunctionOrderBuilder.buy(80,
                                stock("IBM", on("NYSE")), at(125.00)),
                            NestFunctionOrderBuilder.sell(50,
                                stock("GOOGLE", on("NASDAQ")), at(375.00))
                     );

        System.out.println(order);
    }

    @Test
    void step4_람다표현식을_이용한_함수_시퀀싱() {
        Order order = orderForLambda(o -> {
            o.forCustomer("BigBank");
            o.buy( t -> {
                t.quantity(80);
                t.price(125.00);
                t.stock(s -> {
                    s.symbol("IBM");
                    s.market("NYSE");
                });
            });
            o.sell ( t -> {
                t.quantity(80);
                t.price(125.00);
                t.stock(s -> {
                    s.symbol("IBM");
                    s.market("NYSE");
                });
            });
        });

        System.out.println(order);
    }

    @Test
    void step5_여러_DSL패턴_조합하기() {
        Order order = forCustomer2("BigBank", // 최상위 수준 주문의 속성을 지정하는 중첩함수
                buy(t -> t.quantity(80)  // 한 개의 주문을 만드는 람다 표현식
                           .stock("IBM")
                           .on("NYSE")
                           .at(125_00)),
                sell( t -> t.quantity(50)
                            .stock("GOOGLE")
                            .on("NASDAQ")
                            .at(125_00))
                );

        System.out.println(order);
    }


}
