package com.nts.seonghwan.be.comment.controller;

import com.nts.seonghwan.be.comment.dto.CommentCreateRequest;
import com.nts.seonghwan.be.comment.dto.CommentCreateResponse;
import com.nts.seonghwan.be.comment.dto.CommentResponse;
import com.nts.seonghwan.be.comment.service.CommentService;
import com.nts.seonghwan.be.common.utils.ApiUtils;
import com.nts.seonghwan.be.common.utils.ApiUtils.ApiResult;
import com.nts.seonghwan.be.config.security.SessionManagerAttribute;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ApiResult<CommentCreateResponse>> createComment(
            @Validated @RequestBody CommentCreateRequest commentCreateRequest,
            @PathVariable("postId") String postId,
            @SessionManagerAttribute Long userId
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiUtils.success(commentService.createComment(commentCreateRequest, userId, postId))
                );
    }

    @GetMapping
    public ResponseEntity<ApiResult<Page<CommentResponse>>> getComment(
            @PathVariable("postId") String postId,
            Pageable pageable
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiUtils.success(commentService.getComments(postId, pageable))
                );
    }
}
