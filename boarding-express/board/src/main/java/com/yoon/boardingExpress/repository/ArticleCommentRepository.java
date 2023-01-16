package com.yoon.boardingExpress.repository;

import com.yoon.boardingExpress.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}