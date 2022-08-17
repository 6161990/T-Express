package com.yoon.testkick;

public enum IndexType {
    RED,
    GREEN,
    YELLOW;

    public IndexValue of(Object value){
        return IndexValue.of(this, value);
    }
}
