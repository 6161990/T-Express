package com.yoon.boardingExpress.service;

import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.type.SearchType;
import com.yoon.boardingExpress.dto.ArticleDto;
import com.yoon.boardingExpress.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
}