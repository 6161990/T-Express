package com.yoon.boardingExpress.repository;

import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.ArticleComment;
import com.yoon.boardingExpress.domain.Writer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(JpaRepositoryTests.TestJpaConfig.class)
@DataJpaTest
class JpaRepositoryTests {

    private static final Writer ANY_WRITER = Writer.of("name", "email", "phoneNumber", LocalDateTime.now());
    private static final Article ANY_ARTICLE = Article.of(ANY_WRITER, "t","c","h");

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleCommentRepository articleCommentRepository;

    @Autowired
    private WriterRepository writerRepository;


    @Test
    void writerCommentRepository_findAll() {
        List<Writer> actual = writerRepository.findAll();

        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(100);
    }

    @Test
    void writerCommentRepository_save() {
        long prevCount = writerRepository.count();

        writerRepository.save(ANY_WRITER);

        assertThat(writerRepository.findAll().size()).isEqualTo(prevCount+1);
    }

    @Test
    void articleRepository_findAll() {
        List<Article> actual = articleRepository.findAll();

        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(100);
    }

    @Test
    void articleRepository_save() {
        Writer writer = writerRepository.save(ANY_WRITER);
        long prevCount = articleRepository.count();

        articleRepository.save(Article.of(writer, "title", "content", "#romans"));

        assertThat(articleRepository.findAll().size()).isEqualTo(prevCount+1);
    }


    @Test
    void articleCommentRepository_findAll() {
        List<ArticleComment> actual = articleCommentRepository.findAll();

        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(100);
    }

    @Test
    void articleCommentRepository_save() {
        Writer writer = writerRepository.save(ANY_WRITER);
        Article article = articleRepository.save(ANY_ARTICLE);
        long prevCount = articleCommentRepository.count();

        articleCommentRepository.save(ArticleComment.of(article, writer, "test"));

        assertThat(articleCommentRepository.findAll().size()).isEqualTo(prevCount+1);
    }

    @EnableJpaAuditing
    @TestConfiguration
    static class TestJpaConfig {
        @Bean
        AuditorAware<String> auditorAware() {
            return () -> Optional.of("김작가");
        }
    }
}


