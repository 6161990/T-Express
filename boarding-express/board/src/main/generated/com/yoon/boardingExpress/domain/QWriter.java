package com.yoon.boardingExpress.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWriter is a Querydsl query type for Writer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWriter extends EntityPathBase<Writer> {

    private static final long serialVersionUID = 579321847L;

    public static final QWriter writer = new QWriter("writer");

    public final SetPath<ArticleComment, QArticleComment> articleComments = this.<ArticleComment, QArticleComment>createSet("articleComments", ArticleComment.class, QArticleComment.class, PathInits.DIRECT2);

    public final SetPath<Article, QArticle> articles = this.<Article, QArticle>createSet("articles", Article.class, QArticle.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final DateTimePath<java.time.LocalDateTime> signedAt = createDateTime("signedAt", java.time.LocalDateTime.class);

    public QWriter(String variable) {
        super(Writer.class, forVariable(variable));
    }

    public QWriter(Path<? extends Writer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWriter(PathMetadata metadata) {
        super(Writer.class, metadata);
    }

}

