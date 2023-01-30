package com.yoon.boardingExpress.dto.response;

import com.yoon.boardingExpress.dto.ArticleDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String email,
        String name
) implements Serializable {

    public static ArticleResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String name) {
        return new ArticleResponse(id, title, content, hashtag, createdAt, email, name);
    }


    public static ArticleResponse from(ArticleDto dto) {
        String name = dto.userAccountDto().name();
        if (name == null || name.isBlank()) {
            name = dto.userAccountDto().userId();
        }
        return new ArticleResponse(dto.id(), dto.title(), dto.content(), dto.hashtag(), dto.createdAt(), dto.userAccountDto().email(), name);
    }

}
