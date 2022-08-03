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

    public TaxType getTaxType(PurchaseConfirmationType type){
        return this.getProductOptionDetails().stream().filter(l->l.isEqualBy(type.toString()))
                .map(ProductOptionDetail::getTaxType)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Nothing equal type"));
    }
}
