package com.nts.seonghwan.be.post.dto;

import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.preference.dto.PreferenceDto;
import com.nts.seonghwan.be.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostDetailResponse {
    private String postId;
    private String title;
    private String content;
    private String user;
    private String createdAt;
    private List<String> tag;
    private Long viewCount;
    private PreferenceDto like;
    private PreferenceDto dislike;
    private boolean isOwner;


    public PostDetailResponse(Post post, Long viewCount, PreferenceDto like, PreferenceDto dislike, User user){
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user = post.getUser().getEmail();
        this.createdAt = post.getCreatedAt().toString();
        this.tag = post.getTag();
        this.viewCount = viewCount;
        this.like = like;
        this.dislike = dislike;
        this.isOwner = post.getUser().getId().equals(user.getId());
    }
}
