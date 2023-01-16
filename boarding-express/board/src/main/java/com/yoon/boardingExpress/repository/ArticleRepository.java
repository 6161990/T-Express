package com.yoon.boardingExpress.repository;

import com.yoon.boardingExpress.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}