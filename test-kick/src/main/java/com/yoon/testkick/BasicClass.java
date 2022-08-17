package com.yoon.testkick;

import java.time.LocalDateTime;
import java.util.Arrays;
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

    public BasicClass(StudyStatus status) {
        this.status = status;
    }

    public BasicClass(int limit) {
        if(limit <= -10){
            throw new IllegalArgumentException("limit은 0보다 커야한다");
        }
        this.limit = limit;
    }

    public void setPassedAt(LocalDateTime passedAt) {
        this.passedAt = passedAt;
    }

    public void setClassId(ClassId classId) {
        this.classId = classId;
    }

    public void setIv(IndexValue iv) {
        this.iv = iv;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isNotPassed() {
        if(passedAt != null){
            return true;
        }
        return false;
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
