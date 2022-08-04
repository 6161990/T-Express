package com.yoon.TExpress.javaFunctional.practice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductOptionDetail {
    String name;
    long price;
    TaxType taxType;
    List<Props> props;

    public boolean isEqualBy(String purchasedConfirmationType){
        return this.props.stream().anyMatch(i -> i.getName().equals(purchasedConfirmationType));
    }

}
