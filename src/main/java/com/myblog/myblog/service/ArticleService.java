package com.myblog.myblog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myblog.myblog.mapper.ArticleMapper;
import com.myblog.myblog.model.Article;
import com.myblog.myblog.model.PageResult;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class ArticleService {

    private final ArticleMapper articleMapper;

    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    // 创建文章
    public Article create(String title, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreatedAt(java.time.LocalDateTime.now());
        articleMapper.insert(article);
        return article;
    }

    // 根据id查询单篇
    public Optional<Article> findById(Long id) {
        Article article = articleMapper.selectById(id);
        return Optional.ofNullable(article);
    }

    // 更新文章 PUT
    public Article update(Long id, String title, String content) {
        Article article = articleMapper.selectById(id);
        if(article == null) {
            throw new NoSuchElementException("Article not found");
        }
        article.setTitle(title);
        article.setContent(content);
        articleMapper.updateById(article);
        return article;
    }

    // 删除
    public void delete(Long id) {
        Article article = articleMapper.selectById(id);
        if(article == null) {
            throw new NoSuchElementException("Article not found");
        }
        articleMapper.deleteById(id);
    }

    // 分页+搜索，复用Week07模式，同时匹配标题+内容
    public PageResult<Article> search(String keyword, long page, long size) {
        LambdaQueryWrapper<Article> qw = new LambdaQueryWrapper<>();
        if(keyword != null && !keyword.isBlank()){
            qw.like(Article::getTitle, keyword).or().like(Article::getContent, keyword);
        }
        IPage<Article> mpPage = articleMapper.selectPage(new Page<>(page,size), qw);
        return PageResult.of(mpPage);
    }
}