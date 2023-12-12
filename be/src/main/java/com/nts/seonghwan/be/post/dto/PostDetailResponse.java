package com.nts.seonghwan.be.post.dto;

import com.nts.seonghwan.be.post.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDetailResponse {
    private String postId;
    private String title;
    private String content;
    private String user;
    private String createdAt;

    public PostDetailResponse(Post post){
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user = post.getUser().getEmail();
        this.createdAt = post.getCreatedAt().toString();
    }
}