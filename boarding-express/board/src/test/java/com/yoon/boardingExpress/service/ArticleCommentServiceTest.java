package com.yoon.boardingExpress.service;

import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.ArticleComment;
import com.yoon.boardingExpress.domain.UserAccount;
import com.yoon.boardingExpress.dto.ArticleCommentDto;
import com.yoon.boardingExpress.dto.ArticleDto;
import com.yoon.boardingExpress.repository.ArticleCommentRepository;
import com.yoon.boardingExpress.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {


    @InjectMocks
    ArticleCommentService sut;

    @Mock
    ArticleCommentRepository articleCommentRepository;

    @Mock
    ArticleRepository articleRepository;

    @Test
    void 댓글리스트_조회() {
        long articleId = 1L;
        given(articleRepository.findById(articleId))
                .willReturn(Optional.of(Article.of(UserAccount.of("user", "password", "email", "name", "phone", "memo"), "t", "c", "h")));

        List<ArticleCommentDto> actual = sut.searchArticleComment(articleId);

        assertThat(actual).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @Test
    void 댓글조회() {
        ArticleCommentDto actual = sut.find(1L);

        assertThat(actual).isNotNull();
    }

    @Test
    void 댓글작성() {
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(any(ArticleComment.class));

        sut.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(), LocalDateTime.now(), ArticleDto.of(LocalDateTime.now(), UserAccount.of("user", "password", "email", "name", "phone", "memo"), "title", "content", "hashtag"), "content"));

        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }

    @Test
    void 댓글수정() {
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(any(ArticleComment.class));

        sut.updateArticleComment(ArticleCommentDto.of(LocalDateTime.now(), LocalDateTime.now(), ArticleDto.of(LocalDateTime.now(), UserAccount.of("user", "password", "email", "name", "phone", "memo"), "title", "content", "hashtag"), "content"));

        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }

    @Test
    void 댓글삭제() {
        willDoNothing().given(articleCommentRepository).delete(any(ArticleComment.class));

        sut.deleteArticleComment(1L);

        then(articleCommentRepository).should().delete(any(ArticleComment.class));
    }
}