package com.tredlinx.task.article.model.dto;

import com.tredlinx.task.article.domain.comment.model.dto.Comment;
import com.tredlinx.task.article.model.entity.ArticleEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class Article {

    @Getter
    public static class Write {
        private String articleTitle;
        private String articleContents;
    }
    @Getter
    public static class Update {
        private String articleId;
        private String articleTitle;
        private String articleContents;
    }
    @Getter
    public static class Select {
        private String articleId;
        private String articleTitle;
        private String articleContents;
        private List<Comment> comments;

        public static Select articleEntityToDto(ArticleEntity articleEntity) {
            List<Comment> comments = Comment.commentsEntityToDto(articleEntity.getComments().stream()
                    .filter(c -> !c.getIsDelete())
                    .collect(Collectors.toList()));
            return new Select(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getContent(), comments);
        }

        public Select(String articleId, String articleTitle, String articleContents, List<Comment> comments) {
            this.articleId = articleId;
            this.articleTitle = articleTitle;
            this.articleContents = articleContents;
            this.comments = comments;
        }
    }
}
