package com.tredlinx.task.article.model.entity;

import com.tredlinx.task.article.domain.comment.model.entity.CommentEntity;
import com.tredlinx.task.article.model.dto.Article;
import com.tredlinx.task.common.component.JwtUtils;
import com.tredlinx.task.common.exception.CustomRuntimeException;
import com.tredlinx.task.common.exception.model.enumurate.CustomApiCode;
import com.tredlinx.task.common.model.entity.TimeEntity;
import com.tredlinx.task.common.model.enumurate.PointType;
import com.tredlinx.task.user.model.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "ARTICLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity extends TimeEntity {
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

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CommentEntity> comments;

    public ArticleEntity(String id) {
        this.id = id;
    }

    public ArticleEntity(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.user = new UserEntity(JwtUtils.getUid());
        this.comments = new ArrayList<>();
    }

    public static ArticleEntity createArticle(Article.Write write) {
        return new ArticleEntity(write.getArticleTitle(), write.getArticleContents());
    }

    public void updateArticle(Article.Update article) {
        checkMaster();
        this.title = article.getArticleTitle();
        this.content = article.getArticleContents();
    }
    public Article.Select selectArticleDto() {
        return Article.Select.articleEntityToDto(this);
    }

    public void deleteProcess() {
        checkMaster();
        long commentNum = this.getComments().stream()
                .filter(comment -> !comment.getUid().equals(JwtUtils.getUid()))
                .count();
        this.getUser().removePoint(commentNum + PointType.ARTICLE_WRITE.getPoint());
        this.getComments().clear();

    }
    private void checkMaster() {
        if (!this.getUser().getUid().equals(JwtUtils.getUid())) {
            throw new CustomRuntimeException(CustomApiCode.ARTICLE_UNAUTHORIZED);
        }
    }

    public boolean writeComment(String commentsContents) {
        this.getComments().add(new CommentEntity(this.getId(), commentsContents));
        return writePoint();
    }
    private boolean writePoint() {
        if (!this.getUser().getUid().equals(JwtUtils.getUid())) {
            this.getUser().addPoint(PointType.COMMENT_ARTICLE_WRITER);
            return false;
        } else return true;
    }

    public boolean deleteComment(String commentsId) {
        CommentEntity comment = this.getComments().stream()
                .filter(c -> c.getId().equals(commentsId))
                .findAny()
                .orElseThrow(() -> new CustomRuntimeException(CustomApiCode.COMMENT_NOT_FOUND));
        comment.checkMaster();
        comment.delete();

        return comment.getUid().equals(this.getUser().getUid());
    }
}
