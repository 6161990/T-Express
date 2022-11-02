package com.yoon.TExpress.modernJavaInAction.ten.methodReference;

import com.yoon.TExpress.modernJavaInAction.ten.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.yoon.TExpress.modernJavaInAction.ten.methodReference.TaxCalculator.calculate;
import static com.yoon.TExpress.modernJavaInAction.ten.mixedBuilder.MixedBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;

class TaxCalculatorTest {

    Order order;

    @BeforeEach
    void setUp() {
        order = forCustomer2("BigBank", // 최상위 수준 주문의 속성을 지정하는 중첩함수
                buy(t -> t.quantity(80)  // 한 개의 주문을 만드는 람다 표현식
                        .stock("IBM")
                        .on("NYSE")
                        .at(125_00)),
                sell( t -> t.quantity(50)
                        .stock("GOOGLE")
                        .on("NASDAQ")
                        .at(125_00))
        );
    }

    @Test
    void step1() {
        double tax = calculate(order, true, false, true);

        assertThat(tax).isEqualTo(1876875.0000000002);
    }

    @Test
    void step2() {
        double tax = new TaxCalculator()
                .withTaxRegional()
                .withTaxSurcharge()
                .calculate(order); // 이 기법은 코드가 장황하다는 것이 문제다.

        assertThat(tax).isEqualTo(1876875.0000000002);
    }

    @Test
    void step3() {
        double tax = new TaxCalculatorImprove()
                .with(Tax::regional)
                .with(Tax::surcharge)
                .calculate(order);

        assertThat(tax).isEqualTo(1876875.0000000002);
    }
}