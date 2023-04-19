package com.yoon.boardingExpress.dto.response;

import com.yoon.boardingExpress.dto.ArticleCommentDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleCommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String email,
        String name,
        String userId
) implements Serializable {

    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createdAt, String email, String name, String userId) {
        return new ArticleCommentResponse(id, content, createdAt, email, name, userId);
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto) {
        String name = dto.userAccountDto().name();
        if (name == null || name.isBlank()) {
            name = dto.userAccountDto().id();
        }
        return new ArticleCommentResponse(
                dto.id(),
                dto.content(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                name,
                dto.userAccountDto().id());
    }

}

