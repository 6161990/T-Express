package com.yoon.boardingExpress.service;

import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.UserAccount;
import com.yoon.boardingExpress.domain.constant.SearchType;
import com.yoon.boardingExpress.dto.ArticleDto;
import com.yoon.boardingExpress.dto.ArticleWithCommentsDto;
import com.yoon.boardingExpress.dto.UserAccountDto;
import com.yoon.boardingExpress.repository.ArticleRepository;
import com.yoon.boardingExpress.repository.UserAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
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

    @Mock
    private UserAccountRepository userAccountRepository;

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
        given(articleRepository.findByTitleContaining(searchKeyword, pageable)).willReturn(Page.empty());

        Page<ArticleDto> articles = sut.searchArticles(searchType, searchKeyword, pageable);

        assertThat(articles).isEmpty();
        then(articleRepository).should().findByTitleContaining(searchKeyword, pageable);
    }

    @Test
    void 검색어없이_해시태그_검색하면_빈_페이지반환() {
        Pageable pageable = Pageable.ofSize(20);

        Page<ArticleDto> articles = sut.searchArticlesViaHashtag(null, pageable);

        assertThat(articles).isEqualTo(Page.empty(pageable));
        then(articleRepository).shouldHaveNoInteractions();
    }

    @Test
    void 해시태그_검색어로_게시글_검색() {
        String hashtag = "#java";
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findByHashtag(hashtag, pageable)).willReturn(Page.empty());

        Page<ArticleDto> articles = sut.searchArticlesViaHashtag(hashtag, pageable);

        assertThat(articles).isEmpty();
        then(articleRepository).should().findByHashtag(hashtag, pageable);
    }

    @Test
    void 게시글_조회하면_댓글과_함께_반환한다() {
        Long articleId = 1L;
        Article article = createArticle();
        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        ArticleWithCommentsDto dto = sut.getArticleWithComments(articleId);

        assertThat(dto)
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent())
                .hasFieldOrPropertyWithValue("hashtag", article.getHashtag());
        then(articleRepository).should().findById(articleId);
    }

    @Test
    void 댓글_달린_게시글이_없으면_예외를_던진다() {
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.empty());

        Throwable t = catchThrowable(() -> sut.getArticleWithComments(articleId));

        assertThat(t)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("The Article is Not Exist. ArticleId is" + articleId);
        then(articleRepository).should().findById(articleId);
    }


    @Test
    void 게시글을_조회하면_게시글을_반환한다_without_comments() {
        Long articleId = 1L;
        Article article = createArticle();

        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        ArticleDto articleDto = sut.getArticle(articleId);

        assertThat(articleDto)
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent())
                .hasFieldOrPropertyWithValue("hashtag", article.getHashtag());
        then(articleRepository).should().findById(articleId);
    }

    @Test
    void 존재하지_않는_게시글_조회시_예외발생() {
        Long articleId = 0L;

        given(articleRepository.findById(articleId)).willReturn(Optional.empty());

        Throwable t = catchThrowable(() -> sut.getArticleWithComments(articleId));

        assertThat(t).isInstanceOf(EntityNotFoundException.class)
                .hasMessage("The Article is Not Exist. ArticleId is" + articleId);
        then(articleRepository).should().findById(articleId);
    }

    @Test
    void 해시태그를_조회하면_유니크한_해시태그_리스트를_반환한다() {
        List<String> expectedHashtags = List.of("#java", "#spring", "#jpa");
        given(articleRepository.findAllDistinctHashtags()).willReturn(expectedHashtags);

        List<String> hashtags = sut.getHashtags();

        assertThat(hashtags).isEqualTo(expectedHashtags);
        then(articleRepository).should().findAllDistinctHashtags();
    }

    @Test
    void 게시글_수_조회() {
        long expected = 0L;
        given(articleRepository.count()).willReturn(expected);

        long actual = sut.articleCount();

        assertThat(actual).isEqualTo(expected);
        then(articleRepository).should().count();
    }

    @Test
    void 게시글_생성() {
        ArticleDto dto = createArticleDto();
        given(userAccountRepository.getReferenceById(dto.userAccountDto().id())).willReturn(createUserAccount());
        given(articleRepository.save(any(Article.class))).willReturn(createArticle());

        sut.saveArticle(dto);

        then(userAccountRepository).should().getReferenceById(dto.userAccountDto().id());
        then(articleRepository).should().save(any(Article.class));
    }

    @Test
    void 게시글_수정() {
        Article article = createArticle();
        ArticleDto dto = createArticleDto("새 타이틀", "새 내용", "#springboot");
        given(articleRepository.getReferenceById(dto.id())).willReturn(article);

        sut.updateArticle(dto.id(), dto);

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

        sut.updateArticle(dto.id(), dto);

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
        Article article = Article.of(
                createUserAccount(),
                "title",
                "content",
                "#java");
        ReflectionTestUtils.setField(article, "id", 1L);

        return article;
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