package com.yoon.testkick.jUnit;

import lombok.Value;

@Value(staticConstructor = "of")
public class IndexValue {
    IndexType type;
    Object value;

    public <T> T getValue(Class<T> aClass) {
        return aClass.cast(value);
    }


}
