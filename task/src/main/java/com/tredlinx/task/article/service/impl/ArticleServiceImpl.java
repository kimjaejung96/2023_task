package com.tredlinx.task.article.service.impl;

import com.tredlinx.task.article.model.dto.Article;
import com.tredlinx.task.article.model.entity.ArticleEntity;
import com.tredlinx.task.article.repository.ArticleRepo;
import com.tredlinx.task.article.service.ArticleService;
import com.tredlinx.task.common.exception.CustomRuntimeException;
import com.tredlinx.task.common.exception.model.enumurate.CustomApiCode;
import com.tredlinx.task.common.model.enumurate.PointType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepo articleRepo;
    @Override
    @Transactional
    public Map<String, String> articleWrite(Article.Write write) {
        ArticleEntity articleEntity = ArticleEntity.createArticle(write);
        articleEntity = articleRepo.save(articleEntity);
        articleEntity.getUser().addPoint(PointType.ARTICLE_WRITE);

        return Map.of("articleId", articleEntity.getId());
    }

    @Override
    @Transactional
    public Map<String, String> updateArticle(Article.Update updateDto) {
        ArticleEntity articleEntity = articleRepo.findById(updateDto.getArticleId()).orElseThrow(() -> new CustomRuntimeException(CustomApiCode.ARTICLE_NOT_FOUND));
        articleEntity.updateArticle(updateDto);

        return Map.of("articleId", updateDto.getArticleId());
    }

    @Override
    @Transactional(readOnly = true)
    public Article.Select selectArticle(String articleId) {
        ArticleEntity articleEntity = articleRepo.findById(articleId).orElseThrow(() -> new CustomRuntimeException(CustomApiCode.ARTICLE_NOT_FOUND));
        return articleEntity.selectArticleDto();
    }

    @Override
    @Transactional
    public int deleteArticle(String articleId) {
        ArticleEntity articleEntity = articleRepo.findById(articleId).orElseThrow(() -> new CustomRuntimeException(CustomApiCode.ARTICLE_NOT_FOUND));
        articleEntity.deleteProcess();
        articleRepo.delete(articleEntity);
        return 1;
    }
}
