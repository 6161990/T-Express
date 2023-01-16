package com.yoon.boardingExpress.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Entity
@Table(indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "email"),
        @Index(columnList = "phoneNumber")
})
@EntityListeners(AuditingEntityListener.class)
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

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "writer")
    private final Set<Article> articles = new LinkedHashSet<>();

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "writer")
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @Column(nullable = false)
    private LocalDateTime signedAt;

    protected Writer() {}

    public static Writer of(String name, String email, String phoneNumber, LocalDateTime signedAt) {
        return new Writer(name, email, phoneNumber, signedAt);
    }

    private Writer(String name, String email, String phoneNumber, LocalDateTime signedAt) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.signedAt = signedAt;
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
