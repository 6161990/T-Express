package com.yoon.TExpress.chickenAnnotation;

import java.lang.annotation.*;

/** TypeUse , RunTime 를 들어있는 @Chicken 보다 범위가 넓어야한다. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface ChickenContainer {

    Chicken[] value();
}
