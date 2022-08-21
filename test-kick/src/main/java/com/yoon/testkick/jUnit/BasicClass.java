package com.yoon.testkick.jUnit;

import java.time.LocalDateTime;
import java.util.List;

public class BasicClass {

    private StudyStatus status;
    private int limit;
    private LocalDateTime passedAt;
    private ClassId classId;
    private IndexValue iv;
    private List<Book> books;

    public BasicClass() {
        this(StudyStatus.DRAFT);
    }

    public BasicClass(StudyStatus status, int limit, LocalDateTime passedAt, ClassId classId, IndexValue iv, List<Book> books) {
        this.status = status;
        this.limit = limit;
        this.passedAt = passedAt;
        this.classId = classId;
        this.iv = iv;
        this.books = books;
    }

    public BasicClass(StudyStatus status) {
        this.status = status;
    }

    public BasicClass(int limit) {
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

    public boolean isNotPassed() {
        return passedAt == null;
    }

    public ClassId getClassId() {
        return classId;
    }

    public IndexValue getIv() {
        return iv;
    }

    public List<Book> getBooks() {
        return books;
    }
}
