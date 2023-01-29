package com.yoon.boardingExpress.dto;

import com.yoon.boardingExpress.domain.Article;

import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ArticleDto of(Long id, UserAccountDto userAccountDto, String title, String content, String hashtag, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new ArticleDto(id, userAccountDto, title, content, hashtag, createdAt, updatedAt);
    }

    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public Article toEntity() {
        return Article.of(
                userAccountDto.toEntity(),
                title,
                content,
                hashtag
        );
    }

}