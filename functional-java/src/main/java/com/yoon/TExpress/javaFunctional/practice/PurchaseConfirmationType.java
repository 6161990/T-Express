package com.yoon.TExpress.javaFunctional.practice;

public enum PurchaseConfirmationType {
    COMMUNITY,
    MEETING;

    public PurchaseConfirmationValue of(Object value) {
        return PurchaseConfirmationValue.of(this, value);
    }

}
