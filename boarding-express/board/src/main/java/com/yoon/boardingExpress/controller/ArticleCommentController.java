package com.yoon.boardingExpress.controller;

import com.yoon.boardingExpress.dto.UserAccountDto;
import com.yoon.boardingExpress.dto.request.ArticleCommentRequest;
import com.yoon.boardingExpress.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
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
    public String newComments(ArticleCommentRequest request){
        // TODO: 인증 정보를 넣어줘야 한다.
        articleCommentService.saveArticleComment(request.toDto(UserAccountDto.of(
                "yoon", "yoon", "yoon@mail.com", null, null
        )));


        return "redirect:/articles/"+request.articleId();
    }

    @PostMapping ("/{commentId}/delete")
    public String deleteComments(@PathVariable Long commentId, Long articleId) {
        // TODO: 인증 정보를 넣어줘야 한다.
        articleCommentService.deleteArticleComment(commentId);

        return "redirect:/articles/"+articleId;
    }

}
