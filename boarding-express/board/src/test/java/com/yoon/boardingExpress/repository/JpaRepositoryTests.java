package com.yoon.boardingExpress.repository;

import com.yoon.boardingExpress.config.JpaConfig;
import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.ArticleComment;
import com.yoon.boardingExpress.domain.Writer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
@ActiveProfiles("yoontestdb")
이건 동작을 안할 것임.
why? @DataJpaTest 는 자동으로 자신이 지정한 testDB 를 띄워버린다. 이걸 막고 내가 지정한 test 용 db ("yoontestdb") 를 띄우기위해서는
아래 애노테이션을 함께 사용해야한다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
이게 너무 길면 yaml 에 test.database.replace : none 전역적으로 지정해주면된다. */

@Import(JpaConfig.class)
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
    void articleRepository_update() {
        Article article = articleRepository.findById(100L).orElseThrow();
        String updatedHashtag = "#호러";
        article.setHashtag(updatedHashtag);

        Article actual = articleRepository.saveAndFlush(article);
        /**  flush ?
         * test 에서는 기본적으로 rollback 되는데, commit 이전까지는 변경사항이 없으므로 update 쿼리가 나가지 않게된다.
         * 따라서 save 와 동시에 commit 하여 update(변경사항감지) 가 되게끔 flush 를 함께해준다.
         * */

        assertThat(actual).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @Test
    void articleRepository_delete() {
        Article article = articleRepository.findById(100L).orElseThrow();
        long prevArticleCount = articleRepository.count();
        long prevArticleCommentCount = articleCommentRepository.count();
        int deletedCommentSize = article.getArticleComments().size();

        articleRepository.delete(article);

        assertThat(articleRepository.count()).isEqualTo(prevArticleCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(prevArticleCommentCount - deletedCommentSize);
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

}


