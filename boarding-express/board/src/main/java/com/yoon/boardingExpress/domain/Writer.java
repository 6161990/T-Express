package com.yoon.boardingExpress.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Entity
@Table(indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "email"),
        @Index(columnList = "phoneNumber")
})
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    private String email;

    @Setter
    @Column(nullable = false)
    private String phoneNumber;

    private LocalDateTime signedAt;

    protected Writer() {}

    public static Writer of(String name, String email, String phoneNumber) {
        return new Writer(name, email, phoneNumber);
    }

    private Writer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return id !=null && id.equals(writer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
