package com.nts.seonghwan.be.post.dto;

import com.nts.seonghwan.be.post.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCreateResponse {
    private String postId;
    private String title;
    private String content;

    public static PostCreateResponse from(Post post){
        return new PostCreateResponse(post.getPostId(), post.getTitle(), post.getContent());
    }
}
