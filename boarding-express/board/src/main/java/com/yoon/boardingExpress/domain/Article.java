package com.yoon.boardingExpress.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private Writer writer;
    private String title;
    private String content;
    private String hashtag;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
