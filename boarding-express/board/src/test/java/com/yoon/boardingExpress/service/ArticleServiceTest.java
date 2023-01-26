package com.yoon.boardingExpress.service;

import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.UserAccount;
import com.yoon.boardingExpress.domain.type.SearchType;
import com.yoon.boardingExpress.dto.ArticleDto;
import com.yoon.boardingExpress.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks // @Mock 을 주입하는 대상
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    void 게시글_검색() {
        Page<ArticleDto> actual  = sut.searchArticles(SearchType.TITLE, "search");

        assertThat(actual).isNotNull();
    }

    @Test
    void 게시글_조회() {
        ArticleDto actual = sut.find(1L);

        assertThat(actual).isNotNull();
    }

    @Test
    void 게시글_생성() {
        given(articleRepository.save(any(Article.class))).willReturn(any(Article.class));

        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), UserAccount.of("user", "password", "email", "name", "phone", "memo"), "title", "content", "hashtag"));

        then(articleRepository).should().save(any(Article.class));
    }
}