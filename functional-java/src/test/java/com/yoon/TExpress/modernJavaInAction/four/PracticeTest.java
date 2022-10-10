package com.yoon.TExpress.modernJavaInAction.four;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class PracticeTest {

    List<Transaction> transactions;
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    @BeforeEach
    void setUp() {
         transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    void practice1() {
        List<Transaction> actual = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparingInt(Transaction::getValue))
                .collect(toList());

        assertThat(actual).containsExactly(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2011, 400));
    }
}
