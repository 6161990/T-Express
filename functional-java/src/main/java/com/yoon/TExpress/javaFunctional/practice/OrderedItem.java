package com.yoon.TExpress.javaFunctional.practice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderedItem {
    ProductOptionId productOptionId;
    long unitPrice;
    int quantity;
    List<ProductOptionDetail> productOptionDetails;

}
