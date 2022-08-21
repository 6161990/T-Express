package com.yoon.testkick.jUnit;

import java.time.LocalDateTime;

public class Book {
    Genre genre;
    int fee;
    long weeks;
    LocalDateTime borrowedAt;

    public Book(Genre genre, int fee, long weeks, LocalDateTime borrowedAt) {
        this.genre = genre;
        this.fee = fee;
        this.weeks = weeks;
        this.borrowedAt = borrowedAt;
    }

    public Genre getGenre() {
        return Genre.MODERN;
    }
}
