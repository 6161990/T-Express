package com.yoon.boardingExpress.dto.response;

import com.yoon.boardingExpress.dto.ArticleWithCommentsDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String email,
        String name,
        String userId,
        Set<ArticleCommentResponse> articleCommentsResponse
) implements Serializable {

    public static ArticleWithCommentsResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String name, String userId, Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentsResponse(id, title, content, hashtag, createdAt, email, name, userId, articleCommentResponses);
    }

    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto) {
        String name = dto.userAccountDto().name();
        if (name == null || name.isBlank()) {
            name = dto.userAccountDto().id();
        }

        return new ArticleWithCommentsResponse(dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                name,
                dto.userAccountDto().id(),
                dto.articleCommentDtos().stream().map(ArticleCommentResponse::from).collect(Collectors.toCollection(LinkedHashSet::new)));
    }

}
