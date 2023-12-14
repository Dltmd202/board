package com.nts.seonghwan.be.post.controller;

import com.nts.seonghwan.be.common.utils.ApiUtils;
import com.nts.seonghwan.be.common.utils.ApiUtils.ApiResult;
import com.nts.seonghwan.be.config.security.SessionManagerAttribute;
import com.nts.seonghwan.be.post.dto.*;
import com.nts.seonghwan.be.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostApiController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResult<PostCreateResponse>> createPost(
            @Validated @RequestBody PostCreateRequest postCreateRequest,
            @SessionManagerAttribute Long userId
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiUtils.success(postService.savePost(
                        postCreateRequest, userId)));
    }

    @GetMapping
    public ResponseEntity<ApiResult<PostListResponse>> getPost(
            Pageable pageable
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiUtils.success(postService.findPost(pageable)));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResult<PostDetailResponse>> getPost(
            @PathVariable String postId,
            @SessionManagerAttribute Long userId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiUtils.success(postService.getPost(postId, userId)));
    }
}
