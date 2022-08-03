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

    public static ProductOptionDetail of(String name, long price, TaxType taxType, List<Props> props) {
        return new ProductOptionDetail(name, price, taxType, props);
    }

    public boolean isEqualBy(String purchasedConfirmationType){
        return this.props.stream().anyMatch(i -> i.getName().equals(purchasedConfirmationType));
    }

    public String[] getPropsName(){
        return this.getProps().stream().map(Props::getName).toArray(String[]::new);
    }

}
