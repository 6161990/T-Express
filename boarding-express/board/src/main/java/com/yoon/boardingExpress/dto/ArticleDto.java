package com.yoon.boardingExpress.dto;

import com.yoon.boardingExpress.domain.UserAccount;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.yoon.boardingExpress.domain.Article} entity
 */
public record ArticleDto(LocalDateTime createdAt,
                         UserAccount userAccount,
                         String title,
                         String content,
                         String hashtag) implements Serializable {

    public static ArticleDto of(LocalDateTime createdAt, UserAccount userAccount, String title, String content, String hashtag) {
        return new ArticleDto(createdAt, userAccount, title, content, hashtag);
    }

}