package com.yoon.boardingExpress.service;

import com.yoon.boardingExpress.domain.type.SearchType;
import com.yoon.boardingExpress.dto.ArticleCommentDto;
import com.yoon.boardingExpress.dto.ArticleDto;
import com.yoon.boardingExpress.dto.ArticleUpdateDto;
import com.yoon.boardingExpress.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleCommentDto getArticle(Long articleId) {
        return null;
    }

    public void saveArticle(ArticleDto dto) {
    }

    public void updateArticle(ArticleDto dto) {

    }

    public void deleteArticle(Long articleId) {

    }
}
