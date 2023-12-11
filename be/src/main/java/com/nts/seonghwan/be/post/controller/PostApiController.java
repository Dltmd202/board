package com.nts.seonghwan.be.post.controller;

import com.nts.seonghwan.be.common.utils.ApiUtils;
import com.nts.seonghwan.be.common.utils.ApiUtils.ApiResult;
import com.nts.seonghwan.be.config.security.SessionManagerAttribute;
import com.nts.seonghwan.be.post.dto.PostCreateRequest;
import com.nts.seonghwan.be.post.dto.PostCreateResponse;
import com.nts.seonghwan.be.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
