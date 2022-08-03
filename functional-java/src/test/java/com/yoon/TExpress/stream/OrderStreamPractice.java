package com.yoon.TExpress.stream;

import com.yoon.TExpress.javaFunctional.practice.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.yoon.TExpress.javaFunctional.practice.AllocatableItem.of;
import static com.yoon.TExpress.javaFunctional.practice.PurchaseConfirmationType.valueOf;
import static com.yoon.TExpress.javaFunctional.practice.PurchaseConfirmationValue.of;

public class OrderStreamPractice {

    OrderedEvent ordered;
    List<AllocatableItem> items = new ArrayList<>();

    @Test
    void before() {
        ordered.getOrderedItems().stream().map(OrderedItem::getProductOptionDetails)
                .forEach(detail -> detail.forEach(l-> l.getProps().forEach(props -> items.add(AllocatableItem.of(
                        PurchaseConfirmationValue.of(PurchaseConfirmationType.valueOf(props.getName()), props.getValue()),
                        l.getPrice() / l.getProps().size())))
                ));
    }

    @Test
    void after1() {
        for (OrderedItem orderedItem : ordered.getOrderedItems()) { // orderItems [] 하나씩
            List<ProductOptionDetail> detail = orderedItem.getProductOptionDetails(); // orderItems[i] 에서 details 꺼내어
            for (ProductOptionDetail l : detail) {
                for (Props props : l.getProps()) {
                    items.add(of(of(valueOf(props.getName()), props.getValue()), l.getPrice() / l.getProps().size()));
                }
            }
        }
    }

    @Test
    void after2() {
        ordered.getOrderedItems().forEach(p-> p.getProductOptionDetails().forEach(l -> {
            for (Props props : l.getProps())
                items.add(of(of(valueOf(props.getName()), props.getValue()), l.getPrice() / l.getProps().size()));
        }));
    }

    @Test
    void after3() {
        Arrays.stream(ordered.getProductDetailToArray()).forEach(l->{
            for (Props props : l.getProps())
                items.add(of(of(valueOf(props.getName()), props.getValue()), l.getPrice() / l.getProps().size()));
        });
    }

}
