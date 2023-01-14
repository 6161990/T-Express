package com.yoon.boardingExpress.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Article article;
    private Writer writer;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
