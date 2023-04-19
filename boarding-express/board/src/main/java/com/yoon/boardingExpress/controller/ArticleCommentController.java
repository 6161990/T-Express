package com.yoon.boardingExpress.controller;

import com.yoon.boardingExpress.dto.UserAccountDto;
import com.yoon.boardingExpress.dto.request.ArticleCommentRequest;
import com.yoon.boardingExpress.dto.security.BoardPrincipal;
import com.yoon.boardingExpress.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String newComments(@AuthenticationPrincipal BoardPrincipal boardPrincipal,
                              ArticleCommentRequest request) {
            articleCommentService.saveArticleComment(request.toDto(boardPrincipal.toDto()));

            return "redirect:/articles/" + request.articleId();
    }

    @PostMapping ("/{commentId}/delete")
    public String deleteArticleComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            Long articleId
    ) {
        articleCommentService.deleteArticleComment(commentId, boardPrincipal.getUsername());

        return "redirect:/articles/" + articleId;
    }

}
