package com.tredlinx.task.common.model.enumurate;

import lombok.Getter;

@Getter
public enum PointType {
    ARTICLE_WRITE(3),
    COMMENT_WRITER(2),
    COMMENT_ARTICLE_WRITER(1),
    ;


    private final long point;

    PointType(long point) {
        this.point = point;
    }
}
