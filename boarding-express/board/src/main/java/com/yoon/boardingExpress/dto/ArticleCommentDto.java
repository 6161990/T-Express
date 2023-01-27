package com.yoon.boardingExpress.dto;

import java.time.LocalDateTime;

public record ArticleCommentDto(LocalDateTime createdAt,
                                LocalDateTime updatedAt,
                                ArticleDto dto,
                                String content) {

    public static ArticleCommentDto of(LocalDateTime createdAt, LocalDateTime updatedAt, ArticleDto dto, String content) {
        return new ArticleCommentDto(createdAt, updatedAt, dto, content);
    }
}