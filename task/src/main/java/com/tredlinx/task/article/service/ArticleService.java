package com.tredlinx.task.article.service;

import com.tredlinx.task.article.model.dto.Article;

import java.util.Map;

public interface ArticleService {
    Map<String, String> articleWrite(Article.Write write);

    Map<String, String> updateArticle(Article.Update updateDto);
}
