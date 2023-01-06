package com.tredlinx.task.article.domain.comment.service.impl;

import com.tredlinx.task.article.domain.comment.service.CommentService;
import com.tredlinx.task.article.model.entity.ArticleEntity;
import com.tredlinx.task.article.repository.ArticleRepo;
import com.tredlinx.task.common.component.JwtUtils;
import com.tredlinx.task.common.exception.CustomRuntimeException;
import com.tredlinx.task.common.exception.model.enumurate.CustomApiCode;
import com.tredlinx.task.common.model.enumurate.PointType;
import com.tredlinx.task.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final ArticleRepo articleRepo;
    private final UserRepo userRepo;
    @Override
    @Transactional
    public void writeComment(String articleId, String commentsContents) {
        ArticleEntity articleEntity = articleRepo.findById(articleId).orElseThrow(() -> new CustomRuntimeException(CustomApiCode.ARTICLE_NOT_FOUND));
        boolean isMyArticle = articleEntity.writeComment(commentsContents);
        if (!isMyArticle) {
            userRepo.findByUid(JwtUtils.getUid())
                    .ifPresent(user -> user.addPoint(PointType.COMMENT_ARTICLE_WRITER));
        }
    }

    @Override
    @Transactional
    public void deleteComment(String articleId, String commentsId) {
        ArticleEntity articleEntity = articleRepo.findById(articleId).orElseThrow(() -> new CustomRuntimeException(CustomApiCode.ARTICLE_NOT_FOUND));
        boolean isMyArticle = articleEntity.deleteComment(commentsId);

        if (!isMyArticle) {
            userRepo.findByUid(JwtUtils.getUid())
                    .ifPresent(user -> user.removePoint(PointType.COMMENT_ARTICLE_WRITER.getPoint()));
        }

    }
}
