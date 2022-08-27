package com.yoon.testkick.jUnit;

import java.time.LocalDateTime;
import java.util.List;

public class BasicClass {

    private StudyStatus status;
    private String name;
    private int limit;
    private LocalDateTime passedAt;
    private ClassId classId;
    private IndexValue iv;
    private List<Book> books;

    public BasicClass() {
        this(StudyStatus.DRAFT);
    }

    public BasicClass(StudyStatus status, String name, int limit, LocalDateTime passedAt, ClassId classId, IndexValue iv, List<Book> books) {
        this.status = status;
        this.name = name;
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
        this.status = StudyStatus.DRAFT;
        this.limit = limit;
    }

    public BasicClass(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "BasicClass{" +
                "name='" + name + '\'' +
                ", limit=" + limit +
                '}';
    }
}
