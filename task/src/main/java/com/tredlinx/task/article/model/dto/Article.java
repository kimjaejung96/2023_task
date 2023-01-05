package com.tredlinx.task.article.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
