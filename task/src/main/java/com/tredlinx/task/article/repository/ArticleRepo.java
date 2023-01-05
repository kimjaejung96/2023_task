package com.tredlinx.task.article.repository;

import com.tredlinx.task.article.model.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepo extends JpaRepository<ArticleEntity, String> {
    Optional<ArticleEntity> findById(String id);
}
