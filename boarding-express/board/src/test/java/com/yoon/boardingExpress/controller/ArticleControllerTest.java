package com.yoon.boardingExpress.controller;

import com.yoon.boardingExpress.config.SecurityConfig;
import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.type.SearchType;
import com.yoon.boardingExpress.dto.ArticleDto;
import com.yoon.boardingExpress.dto.ArticleWithCommentsDto;
import com.yoon.boardingExpress.dto.UserAccountDto;
import com.yoon.boardingExpress.repository.ArticleRepository;
import com.yoon.boardingExpress.service.ArticleService;
import com.yoon.boardingExpress.service.PaginationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class )
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @MockBean
    private PaginationService paginationService;

    @Test
    void article_list_page_default() throws Exception {
        given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());
        // parameter 를 ArgumentMatchers 로 하나라도 사용했다면 전부 사용해야한다. 그래서 eq() 사용
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0,1,2,3,4));

        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("paginationBarNumbers"));

        then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @Test
    void article_list_page_with_searchType_and_searchValue() throws Exception {
        SearchType searchType = SearchType.TITLE;
        String searchValue = "title";
        given(articleService.searchArticles(eq(searchType), eq(searchValue), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));

        mockMvc.perform(
                        get("/articles")
                                .queryParam("searchType", searchType.name())
                                .queryParam("searchValue", searchValue)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("searchTypes"));

        then(articleService).should().searchArticles(eq(searchType), eq(searchValue), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @Test
    void pagingWithSort() throws Exception {
        String sortName = "title";
        String direction = "desc";
        int pageNumber = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize,  Sort.by(Sort.Order.desc(sortName)));
        List<Integer> actual = List.of(0, 1, 2, 3, 4);

        given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(pageable.getPageNumber(), pageable.getPageSize())).willReturn(actual);

        mockMvc.perform(
                    get("/articles")
                            .queryParam("page", String.valueOf(pageNumber))
                            .queryParam("size", String.valueOf(pageSize))
                            .queryParam("sort", sortName + "," + direction)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("paginationBarNumbers"));

        then(articleService).should().searchArticles(null, null, pageable);
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @Test
    void article_detail_page() throws Exception {
        Long articleId = 101L;
        Long totalCount = 1L;
        given(articleService.getArticle(articleId)).willReturn(createArticleWithCommentsDto());
        given(articleService.articleCount()).willReturn(totalCount);

        mockMvc.perform(get("/articles/" + articleId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"))
                .andExpect(model().attribute("totalCount", totalCount));

        then(articleService).should().getArticle(articleId);
        then(articleService).should().articleCount();
    }

    @Disabled
    @Test
    void article_search_page() throws Exception {
        mockMvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search"));
    }

    @Disabled
    @Test
    void article_search_hashtag_page() throws Exception {
        mockMvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search-hashtag.html"));
    }



    private ArticleDto createArticleDto() {
        return ArticleDto.of(
                1L,
                createUserAccountDto(),
                "title",
                "content",
                "java",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private ArticleWithCommentsDto createArticleWithCommentsDto() {
        return ArticleWithCommentsDto.of(
                101L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                "java",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "test",
                "test",
                "test",
                "testtest@mail.com",
                "test",
                "01099999999",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

}