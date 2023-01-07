package com.tredlinx.task.article.domain.comment.controller;

import com.tredlinx.task.article.domain.comment.service.impl.CommentServiceImpl;
import com.tredlinx.task.common.exception.model.dto.ResponseObject;
import com.tredlinx.task.common.exception.model.enumurate.CustomApiCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Tag(name = "comment", description = "Comment API")
@RestController
@RequestMapping("/v1")
public class CommentController {
    private final CommentServiceImpl commentService;
    @Operation(summary = "comment 작성", description = "comment 작성 API")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
            @ApiResponse(responseCode = "603", description = "글을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "999", description = "SYSTEM_ERROR"),
    })
    @Parameters({
            @Parameter(name = "articleId", description = "글 ID", example = "6cbbede3-47f2-455d-8891-9b0fb90433f2"),
            @Parameter(name = "commentsContents", description = "댓글 내용", example = "첫 댓글 써보네요!!"),
    })
    @PostMapping("/comments")
    public ResponseEntity<ResponseObject> writeComment(@RequestParam String articleId,
                                                 @RequestParam String commentsContents) {
        ResponseObject responseObject = new ResponseObject(CustomApiCode.CREATED);
        commentService.writeComment(articleId, commentsContents);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @Operation(summary = "comment 삭제", description = "comment 삭제 API")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
            @ApiResponse(responseCode = "603", description = "글을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "605", description = "댓글을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "999", description = "SYSTEM_ERROR"),
    })
    @Parameters({
            @Parameter(name = "commentsId", description = "댓글 ID", example = "6cbbede3-47f2-455d-8891-9b0fb90433f2"),
    })
    @DeleteMapping("/comments/{commentsId}")
    public ResponseEntity<ResponseObject> deleteComment(@PathVariable String commentsId,
                                                       @RequestParam String articleId) {
        ResponseObject responseObject = new ResponseObject(CustomApiCode.OK);
        commentService.deleteComment(articleId, commentsId);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
