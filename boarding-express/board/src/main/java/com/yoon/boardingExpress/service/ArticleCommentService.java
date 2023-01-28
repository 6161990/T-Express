package com.yoon.boardingExpress.service;

import com.yoon.boardingExpress.domain.ArticleComment;
import com.yoon.boardingExpress.domain.type.SearchType;
import com.yoon.boardingExpress.dto.ArticleCommentDto;
import com.yoon.boardingExpress.repository.ArticleCommentRepository;
import com.yoon.boardingExpress.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public Page<ArticleComment> searchArticleComment(SearchType searchType, String searchKeyword) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleCommentDto find(long id) {
        return ArticleCommentDto.of(null,null,null, null);
    }

    public void saveArticleComment(ArticleCommentDto dto) {

    }

    public void updateArticleComment(ArticleCommentDto dto) {

    }

    public void deleteArticleComment(long id) {

    }

    public List<ArticleCommentDto> searchArticleComment(long articleId) {
        return List.of();
    }
}
