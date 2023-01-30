package com.yoon.boardingExpress.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.yoon.boardingExpress.domain.ArticleComment;
import com.yoon.boardingExpress.domain.QArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {

    List<ArticleComment> findByArticle_Id(Long articleId); // 게시글 id 를 통해 comment 를 조회하려면 _ (언더바) 를 이용하면 연관관계를 맺고 있는 article 을 알게 된다.

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true); // 리스팅 하지 않은 필드에 대해서는 검색 기능 구현을 제외한다.
        bindings.including(root.userAccount, root.article);
        bindings.bind(root.userAccount.name).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.article.title).first(StringExpression::containsIgnoreCase);
    }
}