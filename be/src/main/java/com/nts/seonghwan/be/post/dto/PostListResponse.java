package com.nts.seonghwan.be.post.dto;

import com.nts.seonghwan.be.post.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostListResponse {
    private String postId;
    private String title;
    private String user;
    private String createdAt;

    public PostListResponse(Post post){
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.user = post.getUser().getEmail();
        this.createdAt = post.getCreatedAt().toString();
    }

}
