package com.tredlinx.task.article.controller;

import com.tredlinx.task.article.model.dto.Article;
import com.tredlinx.task.article.service.impl.ArticleServiceImpl;
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
@Tag(name = "article", description = "Article API")
@RestController
@RequestMapping("/v1")
public class ArticleController {
    private final ArticleServiceImpl articleService;
    @Operation(summary = "글 쓰기", description = "글 쓰기")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
            @ApiResponse(responseCode = "999", description = "SYSTEM_ERROR"),
    })
    @Parameters({
            @Parameter(name = "articleTitle", description = "글 제목", example = "여기는 글제목!"),
            @Parameter(name = "articleContents", description = "글 내용", example = "내용입니다 :)"),
    })
    @PostMapping("/article")
    public ResponseEntity<ResponseObject> articleWrite(@RequestBody Article.Write write) {
        ResponseObject responseObject = new ResponseObject(CustomApiCode.CREATED);
        responseObject.setBody(articleService.articleWrite(write));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @Operation(summary = "글 수정", description = "글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
            @ApiResponse(responseCode = "603", description = "글을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "999", description = "SYSTEM_ERROR"),
    })
    @Parameters({
            @Parameter(name = "articleId", description = "글 아이디", example = "1ded26d4-b3c2-46c1-b11d-55430f24ac75"),
            @Parameter(name = "articleTitle", description = "글 제목", example = "여기는 글제목!"),
            @Parameter(name = "articleContents", description = "글 내용", example = "내용입니다 :)"),
    })
    @PutMapping("/article")
    public ResponseEntity<ResponseObject> updateArticle(@RequestBody Article.Update updateDto) {
        ResponseObject responseObject = new ResponseObject(CustomApiCode.CREATED);
        responseObject.setBody(articleService.updateArticle(updateDto));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

}
