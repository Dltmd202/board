package com.nts.seonghwan.be.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class PostListResponse {
    private Page<PostListResponseElement> posts;
    private long totalCommentCount;
}
