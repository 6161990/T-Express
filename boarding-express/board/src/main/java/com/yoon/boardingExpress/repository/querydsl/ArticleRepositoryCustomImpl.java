package com.yoon.boardingExpress.repository.querydsl;

import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.QArticle;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom{
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public ArticleRepositoryCustomImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle qArticle = QArticle.article;

        return from(qArticle)
                .distinct()
                .select(qArticle.hashtag)
                .where(qArticle.hashtag.isNotNull())
                .fetch();
    }
}
