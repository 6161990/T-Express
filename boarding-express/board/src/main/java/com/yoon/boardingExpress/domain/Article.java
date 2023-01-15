package com.yoon.boardingExpress.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Entity
@Table(indexes = {
        @Index(columnList = "writer"),
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt")
})
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Writer writer; // Todo 필요한가 확인

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false, length = 10000)
    private String content;

    @Setter
    private String hashtag;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @CreatedBy
    private Long writerId;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Article() {}

    public static Article of(String title, String content, String hashtag) {
        return new Article(title,content,hashtag);
    }

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id != null && id.equals(article.id); // id 가 null 인 경우는 영속화할 수 없는 상태로 간주하겠다는 의미
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
