package com.yoon.boardingExpress.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.yoon.boardingExpress.domain.ArticleComment;
import com.yoon.boardingExpress.domain.QArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true); // 리스팅 하지 않은 필드에 대해서는 검색 기능 구현을 제외한다.
        bindings.including(root.writer, root.article);
        bindings.bind(root.writer.name).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.article.title).first(StringExpression::containsIgnoreCase);
    }
}