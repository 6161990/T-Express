package com.yoon.boardingExpress.dto;

import com.yoon.boardingExpress.domain.UserAccount;

import java.time.LocalDateTime;

public record ArticleDto(LocalDateTime createdAt,
                         UserAccount userAccount,
                         String title,
                         String content,
                         String hashtag) {

    public static ArticleDto of(LocalDateTime createdAt, UserAccount userAccount, String title, String content, String hashtag) {
        return new ArticleDto(createdAt, userAccount, title, content, hashtag);
    }

}