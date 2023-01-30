package com.yoon.boardingExpress.service;

import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.UserAccount;
import com.yoon.boardingExpress.domain.type.SearchType;
import com.yoon.boardingExpress.dto.ArticleCommentDto;
import com.yoon.boardingExpress.dto.ArticleDto;
import com.yoon.boardingExpress.dto.UserAccountDto;
import com.yoon.boardingExpress.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks // @Mock 을 주입하는 대상
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    void 검색어없이_게시글_검색하면_게시글_페이지반환() {
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

        assertThat(articles).isEmpty();
        then(articleRepository).should().findAll(pageable);
    }

    @Test
    void 검색어로_게시글_검색() {
        SearchType searchType = SearchType.TITLE;
        String searchKeyword = "title";
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findByTitle(searchKeyword, pageable)).willReturn(Page.empty());

        Page<ArticleDto> articles = sut.searchArticles(searchType, searchKeyword, pageable);

        assertThat(articles).isEmpty();
        then(articleRepository).should().findByTitle(searchKeyword, pageable);
    }

    @Test
    void 게시글_조회() {
        Long articleId = 1L;
        Article article = createArticle();
        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        ArticleCommentDto dto = sut.getArticle(1L);

        assertThat(dto)
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent())
                .hasFieldOrPropertyWithValue("hashtag", article.getHashtag());
        then(articleRepository).should().findById(articleId);
    }

    @Test
    void 존재하지_않는_게시글_조회시_예외발생() {
        Long articleId = 0L;

        given(articleRepository.findById(articleId)).willReturn(Optional.empty());

        Throwable t = catchThrowable(() -> sut.getArticle(articleId));

        assertThat(t).isInstanceOf(EntityNotFoundException.class)
                .hasMessage("The Article is Not Exist. ArticleId is" + articleId);
        then(articleRepository).should().findById(articleId);
    }

    @Test
    void 게시글_생성() {
        ArticleDto dto = createArticleDto();
        given(articleRepository.save(any(Article.class))).willReturn(createArticle());

        sut.saveArticle(dto);

        then(articleRepository).should().save(any(Article.class));
    }

    @Test
    void 게시글_수정() {
        Article article = createArticle();
        ArticleDto dto = createArticleDto("새 타이틀", "새 내용", "#springboot");
        given(articleRepository.getReferenceById(dto.id())).willReturn(article);

        sut.updateArticle(dto);

        assertThat(article)
                .hasFieldOrPropertyWithValue("title", dto.title())
                .hasFieldOrPropertyWithValue("content", dto.content())
                .hasFieldOrPropertyWithValue("hashtag", dto.hashtag());
        then(articleRepository).should().getReferenceById(dto.id());
    }

    @Test
    void 게시글이_존재하지않을때_게시글_수정시도() {
        ArticleDto dto = createArticleDto("새 타이틀", "새 내용", "#springboot");
        given(articleRepository.getReferenceById(dto.id())).willThrow(EntityNotFoundException.class);

        sut.updateArticle(dto);

        then(articleRepository).should().getReferenceById(dto.id());
    }

    @Test
    void 게시글_삭제() {
        Long articleId = 1L;
        willDoNothing().given(articleRepository).deleteById(articleId);

        sut.deleteArticle(articleId);

        then(articleRepository).should().deleteById(articleId);
    }


    private UserAccount createUserAccount() {
        return UserAccount.of(
                "test",
                "test",
                "test@mail.com",
                "test",
                "01050505050",
                "memo"
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

    private ArticleDto createArticleDto() {
        return createArticleDto("title", "content", "#java");
    }

    private ArticleDto createArticleDto(String title, String content, String hashtag) {
        return ArticleDto.of(1L,
                createUserAccountDto(),
                title,
                content,
                hashtag,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                1L,
                "test",
                "test",
                "test@mail.com",
                "test",
                "01066666666",
                "This is memo",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}