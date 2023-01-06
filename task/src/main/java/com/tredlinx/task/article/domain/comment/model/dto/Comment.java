package com.tredlinx.task.article.domain.comment.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tredlinx.task.article.domain.comment.model.entity.CommentEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Comment {
    private String commentsId;
    private String commentsContents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDt;


    public Comment(String commentsId, String commentsContents, LocalDateTime createDt) {
        this.commentsId = commentsId;
        this.commentsContents = commentsContents;
        this.createDt = createDt;
    }

    public static List<Comment> commentsEntityToDto(List<CommentEntity> commentEntities) {
        List<Comment> comments = new ArrayList<>();
        commentEntities.forEach(comment -> comments.add(
                new Comment(comment.getId(), comment.getContent(), comment.getCreateTime())));
        return comments;
    }
}
