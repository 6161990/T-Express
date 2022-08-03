package com.yoon.TExpress.javaFunctional.practice;

import lombok.Value;

@Value(staticConstructor = "of")
public class OrderId {
    String id;
}
