package com.yoon.TExpress.javaFunctional.practice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderedEvent {
    OrderId orderId;
    List<OrderedItem> orderedItems;

  
}
