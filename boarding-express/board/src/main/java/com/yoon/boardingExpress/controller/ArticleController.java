package com.yoon.boardingExpress.controller;

import com.yoon.boardingExpress.domain.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String articles(ModelMap map){
        map.addAttribute("articles", List.of());
        return "articles/index";
    }

    @GetMapping("/{id}")
    public String detail(@PathParam("id") Long id, ModelMap map){
        map.addAttribute("article", Article.of(null, null, null, null));
        map.addAttribute("articleComments", List.of());
        return "articles/detail";
    }

    @GetMapping("/search")
    public String search(){
        return "articles/search";
    }

    @GetMapping("/search-hashtag")
    public String search_hashtag(){
        return "articles/search-hashtag.html";
    }

}
