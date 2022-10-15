package com.yoon.TExpress.javaFunctional.practice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class OrderedEvent {
    OrderId orderId;
    List<OrderedItem> orderedItems;

}
