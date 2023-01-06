package com.tredlinx.task.article.domain.comment.service;

public interface CommentService {
    void writeComment(String articleId, String commentsContents);

    void deleteComment(String articleId, String commentsId);
}
