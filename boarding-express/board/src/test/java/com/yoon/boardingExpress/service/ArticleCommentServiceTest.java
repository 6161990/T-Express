package com.yoon.boardingExpress.service;

import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.ArticleComment;
import com.yoon.boardingExpress.domain.UserAccount;
import com.yoon.boardingExpress.dto.ArticleCommentDto;
import com.yoon.boardingExpress.dto.UserAccountDto;
import com.yoon.boardingExpress.repository.ArticleCommentRepository;
import com.yoon.boardingExpress.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

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
        Long articleId = 1L;
        ArticleComment expected = createArticleComment("content");

        given(articleCommentRepository.findByArticle_Id(articleId)).willReturn(List.of(expected));

        List<ArticleCommentDto> actual = sut.searchArticleComments(articleId);

        assertThat(actual).hasSize(1).first().hasFieldOrPropertyWithValue("content", expected.getContent());
        then(articleRepository).should().findById(articleId);
    }

    @Test
    void 댓글작성() {
        ArticleCommentDto dto = createArticleCommentDto("댓글");

        given(articleRepository.getReferenceById(dto.articleId())).willReturn(createArticle());
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        sut.saveArticleComment(dto);

        then(articleRepository).should().getReferenceById(dto.articleId());
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }

    @Test
    void 존재하지_않는_게시글에_댓글_저장_시도() {
        ArticleCommentDto dto = createArticleCommentDto("댓글");

        given(articleRepository.getReferenceById(dto.articleId())).willThrow(EntityNotFoundException.class);

        sut.saveArticleComment(dto);

        then(articleRepository).should().getReferenceById(dto.articleId());
        then(articleCommentRepository).shouldHaveNoInteractions();
    }

    @Test
    void 댓글수정() {
        String prevContent = "prevContent";
        String updatedContent = "updatedContent";
        ArticleComment articleComment = createArticleComment(prevContent);
        ArticleCommentDto dto = createArticleCommentDto(updatedContent);

        given(articleCommentRepository.getReferenceById(dto.id())).willReturn(articleComment);

        sut.updateArticleComment(dto);

        assertThat(articleComment.getContent())
                .isNotEqualTo(prevContent)
                .isEqualTo(updatedContent);
        then(articleCommentRepository).should().getReferenceById(dto.id());
    }

    @Test
    void 댓글이_존재하지않을때_댓글_수정_시도() {
        ArticleCommentDto dto = createArticleCommentDto("댓글");
        given(articleCommentRepository.getReferenceById(dto.id())).willThrow(EntityNotFoundException.class);

        sut.updateArticleComment(dto);

        then(articleCommentRepository).should().getReferenceById(dto.id());
    }

    @Test
    void 댓글_삭제() {
        Long articleCommentId = 1L;
        willDoNothing().given(articleCommentRepository).deleteById(articleCommentId);

        sut.deleteArticleComment(articleCommentId);

        then(articleCommentRepository).should().deleteById(articleCommentId);
    }

    private ArticleCommentDto createArticleCommentDto(String content) {
        return ArticleCommentDto.of(
                1L,
                1L,
                createUserAccountDto(),
                content,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "test",
                "test",
                "test@mail.com",
                "test",
                "01077787778",
                "This is memo",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private ArticleComment createArticleComment(String content) {
        return ArticleComment.of(
                Article.of(createUserAccount(), "title", "content", "hashtag"),
                createUserAccount(),
                content
        );
    }

    private UserAccount createUserAccount() {
        return UserAccount.of(
                "test",
                "test",
                "test@mail.com",
                "test",
                "01077787778",
                null
        );
    }

    private Article createArticle() {
        return Article.of(
                createUserAccount(),
                "title",
                "content",
                "#java"
        );
    }
}