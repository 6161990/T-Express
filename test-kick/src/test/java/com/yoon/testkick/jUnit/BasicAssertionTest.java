package com.yoon.testkick.jUnit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.yoon.testkick.jUnit.BasicAssertion.assertNotPassThat;
import static java.time.LocalDateTime.now;

public class BasicAssertionTest {

    @Test
    void assertNotPassThat_test() {
        List<BasicClass> actual = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        Book b1= new Book(Genre.MODERN, 0, 2, now());
        books.add(b1);
        BasicClass a1 = new BasicClass(StudyStatus.STARTED, "math",2, null, ClassId.of("id"), IndexValue.of(IndexType.RED, "redred"), books);
        actual.add(a1);

        assertNotPassThat(actual, ClassId.of("id")).hasSize(1);
    }

}
