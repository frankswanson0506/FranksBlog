package com.myblog.myblog.controller;

import com.myblog.myblog.model.Article;
import com.myblog.myblog.model.PageResult;
import com.myblog.myblog.model.Result;
import com.myblog.myblog.service.ArticleService;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 新建文章 POST /articles
    @PostMapping("/articles")
    public Result<Article> create(@RequestBody Article req) {
        Article article = articleService.create(req.getTitle(), req.getContent());
        return Result.ok(article);
    }

    // 查询单篇 GET /articles/{id}
    @GetMapping("/articles/{id}")
    public Result<Article> getOne(@PathVariable Long id) {
        Optional<Article> opt = articleService.findById(id);
        Article article = opt.orElseThrow(() -> new NoSuchElementException("Article not found"));
        return Result.ok(article);
    }

    // 更新 PUT /articles/{id}
    @PutMapping("/articles/{id}")
    public Result<Article> update(@PathVariable Long id, @RequestBody Article req) {
        Article updated = articleService.update(id, req.getTitle(), req.getContent());
        return Result.ok(updated);
    }

    // 删除 DELETE /articles/{id}
    @DeleteMapping("/articles/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        articleService.delete(id);
        return Result.ok(null);
    }

    // 搜索分页 GET /articles
    @GetMapping("/articles")
    public Result<PageResult<Article>> list(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") long page,
            @RequestParam(defaultValue = "5") long size
    ){
        PageResult<Article> pageResult = articleService.search(keyword, page, size);
        return Result.ok(pageResult);
    }
}