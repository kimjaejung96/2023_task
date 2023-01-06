package com.tredlinx.task.article.domain.comment.model.entity;

import com.tredlinx.task.article.model.entity.ArticleEntity;
import com.tredlinx.task.common.model.entity.TimeEntity;
import com.tredlinx.task.user.model.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "COMMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity extends TimeEntity {
    @Id
    @Column(name = "ID", columnDefinition = "char(36)")
    private String id;
    @Column(name = "CONTENT", columnDefinition = "VARCHAR(200)")
    private String content;
    @CreatedDate
    @Column(name = "CREATE_DT", nullable = false, updatable = false, columnDefinition = "DATETIME")
    private LocalDateTime createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICLE_ID",  referencedColumnName = "ID")
    private ArticleEntity article;

}
