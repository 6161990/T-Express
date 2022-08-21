package com.yoon.testkick.jUnit;

import org.assertj.core.api.Assertions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicAssertion {

    private final List<BasicClass> basicClasses;

    public BasicAssertion(List<BasicClass> basicClasses) {
        this.basicClasses = basicClasses;
    }

    public static BasicAssertion assertNotPassThat(
            List<BasicClass> actual,
            ClassId classId) {
        assertThat(actual).isNotEmpty();

        return new BasicAssertion(actual)
                .isNotPassed()
                .classId(classId);
    }

    private BasicAssertion classId(ClassId classId) {
        for (BasicClass b : basicClasses){
            assertThat(b.getClassId()).isEqualTo(classId);
        }
        return this;
    }

    private BasicAssertion isNotPassed() {
        for (BasicClass b : basicClasses){
            assertThat(b.isNotPassed()).isTrue();
        }
        return this;
    }

    public BasicAssertion hasSize(int size){
        Assertions.assertThat(basicClasses).hasSize(size);
        return this;
    }

    public static Book borrow(Genre genre, long weeks, LocalDateTime borrowedAt){
        return borrow(genre, 0, weeks, borrowedAt);
    }

    public static Book returning(Genre genre, long weeks, LocalDateTime returnedAt){
        return returning(genre, 0, weeks, returnedAt);
    }

    private static Book returning(Genre genre, int fee, long weeks, LocalDateTime returnedAt) {
        return book(genre, fee, weeks, returnedAt);
    }

    private static Book borrow(Genre genre, int fee, long weeks, LocalDateTime borrowedAt) {
        return book(genre, fee, weeks, borrowedAt);
    }

    private static Book book(Genre genre, int fee, long weeks, LocalDateTime borrowedAt) {
        return new Book(genre, fee, weeks, borrowedAt);
    }

    private List<Book> find(IndexValue iv, Genre genre){
        return basicClasses.stream()
                .filter(r-> iv.equals(r.getIv()))
                .flatMap(r->r.getBooks().stream())
                .filter(l->genre.equals(l.getGenre()))
                .collect(Collectors.toList());
    }

    public BasicAssertion has(IndexValue iv, Book ... books){
        List<BasicClass> find = basicClasses.stream().filter(r -> iv.equals(r.getIv())).collect(Collectors.toList());

        Assertions.assertThat(find).hasSize(1);

        BasicClass basicClass = find.get(0);
        assertThat(basicClass.getBooks()).containsExactly(books);

        return this;
    }
}
