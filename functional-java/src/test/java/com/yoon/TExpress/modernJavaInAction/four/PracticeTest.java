package com.yoon.TExpress.modernJavaInAction.four;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
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

    @Test
    void practice2() {
        List<String> actual = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());

        assertThat(actual).containsExactly("Cambridge", "Milan");
    }

    @Test
    void practice3() {
        List<Trader> actual = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        assertThat(actual).containsExactly(alan, brian, raoul);
    }

    @Test
    void practice4() {
        String actual = transactions.stream().map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", new BinaryOperator<String>() {
                    @Override
                    public String apply(String n1, String n2) {
                        return n1 + n2;
                    }
                });

        assertThat(actual).isEqualTo("AlanBrianMarioRaoul");
    }

    @Test
    void practice5() {
        boolean actual = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milano"));

        assertThat(actual).isFalse();
    }

    @Test
    void practice6() {
        transactions.stream().filter(t->t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    @Test
    void practice7() {
        Optional<Integer> actual = transactions.stream().map(t -> t.getValue()).reduce(Integer::max);

        assertThat(actual.get()).isEqualTo(1000);
    }

    @Test
    void practice8() {
        Optional<Integer> actual = transactions.stream().map(t -> t.getValue()).reduce(Integer::min);
        Optional<Transaction> actual2 = transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        Optional<Transaction> actual3 = transactions.stream().min(comparing(Transaction::getValue));

        assertThat(actual.get()).isEqualTo(300);
        assertThat(actual.get()).isEqualTo(actual2.get().getValue()).isEqualTo(actual3.get().getValue());
    }


}
