package com.yoon.boardingExpress.controller;

import com.yoon.boardingExpress.domain.type.SearchType;
import com.yoon.boardingExpress.dto.response.ArticleResponse;
import com.yoon.boardingExpress.dto.response.ArticleWithCommentsResponse;
import com.yoon.boardingExpress.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchKeyword,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map){

        map.addAttribute("articles", articleService.searchArticles(searchType, searchKeyword, pageable).map(ArticleResponse::from));
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String detail(@PathVariable("articleId") Long articleId, ModelMap map){
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticle(articleId));
        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.articleCommentsResponse());
        return "articles/detail";
    }

    @GetMapping("/search")
    public String search(){
        return "articles/search";
    }

    @GetMapping("/search-hashtag")
    public String search_hashtag(){
        return "articles/search-hashtag";
    }

}
