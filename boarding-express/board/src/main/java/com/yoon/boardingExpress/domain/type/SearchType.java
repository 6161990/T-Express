package com.yoon.boardingExpress.domain.type;

import lombok.Getter;

public enum SearchType {
    TITLE("제목"),
    CONTENT("본문"),
    ID("유저 ID"),
    NAME("이름"),
    HASHTAG("해시태그");

    @Getter private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
