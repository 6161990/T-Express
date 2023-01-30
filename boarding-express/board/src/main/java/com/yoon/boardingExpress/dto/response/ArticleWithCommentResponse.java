package com.yoon.boardingExpress.dto.response;

import com.yoon.boardingExpress.dto.ArticleWithCommentsDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String email,
        String name,
        Set<ArticleCommentResponse> articleCommentResponses
) implements Serializable {

    public static ArticleWithCommentResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String name, Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentResponse(id, title, content, hashtag, createdAt, email, name, articleCommentResponses);
    }

    public static ArticleWithCommentResponse from(ArticleWithCommentsDto dto) {
        String name = dto.userAccountDto().name();
        if (name == null || name.isBlank()) {
            name = dto.userAccountDto().userId();
        }

        return new ArticleWithCommentResponse(dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                name,
                dto.articleCommentDtos().stream().map(ArticleCommentResponse::from).collect(Collectors.toCollection(LinkedHashSet::new)));
    }

}
