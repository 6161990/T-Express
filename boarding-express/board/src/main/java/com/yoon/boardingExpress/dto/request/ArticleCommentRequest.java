package com.yoon.boardingExpress.dto.request;

import com.yoon.boardingExpress.dto.ArticleCommentDto;
import com.yoon.boardingExpress.dto.UserAccountDto;

public record ArticleCommentRequest(Long articleId, String content) {

    public static ArticleCommentRequest of(Long articleId, String content){
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto){
        return ArticleCommentDto.of(articleId, userAccountDto, content);
    }
}
