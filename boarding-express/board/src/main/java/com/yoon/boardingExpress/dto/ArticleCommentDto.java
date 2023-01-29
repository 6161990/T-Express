package com.yoon.boardingExpress.dto;

import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.ArticleComment;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ArticleCommentDto of(Long id, Long articleId, UserAccountDto userAccountDto, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new ArticleCommentDto(id, articleId, userAccountDto, content, createdAt, updatedAt);
    }

    public static ArticleCommentDto from(ArticleComment entity) {
        return new ArticleCommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public ArticleComment toEntity(Article entity) {
        return ArticleComment.of(
                entity,
                userAccountDto.toEntity(),
                content
        );
    }

}