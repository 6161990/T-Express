package com.yoon.boardingExpress.service;

import com.yoon.boardingExpress.domain.ArticleComment;
import com.yoon.boardingExpress.dto.ArticleCommentDto;
import com.yoon.boardingExpress.repository.ArticleCommentRepository;
import com.yoon.boardingExpress.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(Long articleId) {
        return articleCommentRepository.findByArticle_Id(articleId)
                .stream()
                .map(ArticleCommentDto::from)
                .toList();
    }

    public void saveArticleComment(ArticleCommentDto dto) {
        try{
            articleCommentRepository.save(dto.toEntity(articleRepository.getReferenceById(dto.articleId())));
        }catch (EntityNotFoundException e){
            log.warn("댓글 저장 실패. 댓글의 게시글을 찾을 수 없습니다 - dto: {}", dto);
        }
    }

    public void updateArticleComment(ArticleCommentDto dto) {
        try{
            ArticleComment articleComment = articleCommentRepository.getReferenceById(dto.articleId());
            if(dto.content() != null){
                articleComment.setContent(dto.content());
            }
        }catch (EntityNotFoundException e){
            log.warn("댓글 업데이트 실패. 댓글을 찾을 수 없습니다 - dto: {}", dto);
        }
    }

    public void deleteArticleComment(Long id) {
        articleCommentRepository.deleteById(id);
    }

}
