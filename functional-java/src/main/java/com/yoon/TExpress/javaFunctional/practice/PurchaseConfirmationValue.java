package com.yoon.TExpress.javaFunctional.practice;

import lombok.Value;

@Value(staticConstructor = "of")
public class PurchaseConfirmationValue {
    PurchaseConfirmationType type;
    Object value;

    public <T> T getValue(Class<T> aClass) {
        return aClass.cast(value);
    }


}
