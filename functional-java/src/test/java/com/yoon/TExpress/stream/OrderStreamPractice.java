package com.yoon.TExpress.stream;

import com.yoon.TExpress.javaFunctional.practice.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.yoon.TExpress.javaFunctional.practice.AllocatableItem.of;
import static com.yoon.TExpress.javaFunctional.practice.PurchaseConfirmationType.valueOf;
import static com.yoon.TExpress.javaFunctional.practice.PurchaseConfirmationValue.of;

public class OrderStreamPractice {

    OrderedEvent ordered;
    List<AllocatableItem> items = new ArrayList<>();

    @BeforeEach
    void setUp() {
        List<OrderedItem> orderedItems = new ArrayList<>();
        List<ProductOptionDetail> details = new ArrayList<>();
        List<Props> prop = new ArrayList<>();
        prop.add(new Props("COMMUNITY", "COMMUNITY-M1"));
        prop.add(new Props("MEETING", "MEETING1"));
        List<Props> prop2 = new ArrayList<>();
        prop2.add(new Props("COMMUNITY", "COMMUNITY-M2"));
        prop2.add(new Props("MEETING", "MEETING2"));
        ProductOptionDetail productOption = new ProductOptionDetail("COMMUNITY_SERVICE", 2000, TaxType.VAT, prop);
        ProductOptionDetail productOption2 = new ProductOptionDetail("MEETING_SERVICE", 200, TaxType.VAT, prop2);
        details.add(productOption);
        details.add(productOption2);
        OrderedItem item1 = new OrderedItem(ProductOptionId.of("productOptionId"), 2200, 1, details);
        orderedItems.add(item1);
        ordered = new OrderedEvent(OrderId.of("orderId"), orderedItems);
    }

    @Test
    void before() {
        ordered.getOrderedItems().stream().map(OrderedItem::getProductOptionDetails)
                .forEach(detail -> detail
                    .forEach(l -> l.getProps()
                        .forEach(props -> items.add(AllocatableItem.of(
                                PurchaseConfirmationValue.of(PurchaseConfirmationType.valueOf(props.getName()), props.getValue()),
                                l.getPrice() / detail.size())))
                    ));
    }

    @Test
    void after1() {
        for (OrderedItem orderedItem : ordered.getOrderedItems()) { // orderItems [] 하나씩
            List<ProductOptionDetail> detail = orderedItem.getProductOptionDetails(); // orderItems[i] 에서 details 꺼내어
            for (ProductOptionDetail l : detail) {
                for (Props props : l.getProps()) {
                    items.add(of(of(valueOf(props.getName()), props.getValue()), l.getPrice() / detail.size()));
                }
            }
        }
    }

    @Test
    void after2() {
        ordered.getOrderedItems().forEach(p -> p.getProductOptionDetails().forEach(l -> {
            for (Props props : l.getProps())
                items.add(of(of(valueOf(props.getName()), props.getValue()), l.getPrice() / l.getProps().size()));
        }));
    }

    @Test
    void after4_flatMap() {
        List<ProductOptionDetail> flatDetail
                = ordered.getOrderedItems().stream().flatMap(i -> i.getProductOptionDetails().stream()).collect(Collectors.toList());
        System.out.println(flatDetail);
        System.out.println("------------------------------------");

        List<List<ProductOptionDetail>> simpleDetail = ordered.getOrderedItems().stream().map(OrderedItem::getProductOptionDetails).collect(Collectors.toList());
        System.out.println(simpleDetail);

        List<Props> propsList = flatDetail.stream().flatMap(e -> e.getProps().stream()).collect(Collectors.toList());
        System.out.println(propsList);
        List<List<Props>> propsListList = flatDetail.stream().map(e -> e.getProps()).collect(Collectors.toList());
        System.out.println(propsListList);
    }

    @Test
    void after5_forEach_or_flatMap() {
        ordered.getOrderedItems().stream()
                .flatMap(i1 -> i1.getProductOptionDetails().stream())
                .collect(Collectors.toList())
                .forEach(i -> i.getProps().forEach(
                        props -> {
                            items.add(of(of(valueOf(props.getName()), props.getValue()),
                                    i.getPrice() / i.getProps().size()));
                        }));

        System.out.println(items);

        System.out.println("------------------------------------");

        List<AllocatableItem> items2 = new ArrayList<>();
        ordered.getOrderedItems().forEach(p -> p.getProductOptionDetails().forEach(l -> {
            for (Props props : l.getProps())
                items2.add(of(of(valueOf(props.getName()), props.getValue()), l.getPrice() / l.getProps().size()));
        }));
        System.out.println(items2);
    }

}