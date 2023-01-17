package com.yoon.boardingExpress.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(indexes = {
        @Index(columnList = "writer_id")
})
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "article_id")
    @ManyToOne(optional = false)
    private Article article;

    @ManyToOne(optional = false)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    @Setter
    @Column(nullable = false, length = 1000)
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected ArticleComment() {}

    public static ArticleComment of(Article article, Writer writer, String content) {
        return new ArticleComment(article, writer, content);
    }

    private ArticleComment(Article article, Writer writer, String content) {
        this.article = article;
        this.writer = writer;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return id!=null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
