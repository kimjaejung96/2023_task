package com.tredlinx.task.article.model.entity;

import com.tredlinx.task.article.model.dto.Article;
import com.tredlinx.task.common.component.JwtUtils;
import com.tredlinx.task.common.model.enumurate.PointType;
import com.tredlinx.task.user.model.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Table(name = "ARTICLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity {
    @Id
    @Column(name = "ID", columnDefinition = "char(36)")
    private String id;
    @Column(name = "TITLE", columnDefinition = "VARCHAR(100)")
    private String title;
    @Column(name = "CONTENT", columnDefinition = "VARCHAR(200)")
    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID",  referencedColumnName = "UID")
    private UserEntity user;

    public ArticleEntity(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.user = new UserEntity(JwtUtils.getUid());
    }

    public static ArticleEntity createArticle(Article.Write write) {
        return new ArticleEntity(write.getArticleTitle(), write.getArticleContents());
    }

    public void updateArticle(Article.Update article) {
        this.title = article.getArticleTitle();
        this.content = article.getArticleContents();

    }

}
