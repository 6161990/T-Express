package com.yoon.testkick;

public class Basic {

    private StudyStatus status;
    private int limit;

    public Basic() {
        this(StudyStatus.DRAFT);
    }

    public Basic(StudyStatus status) {
        this.status = status;
    }

    public Basic(int limit) {
        if(limit <= -10){
            throw new IllegalArgumentException("limit은 0보다 커야한다");
        }
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }
}
