package com.nts.seonghwan.be.post.dto;

import com.nts.seonghwan.be.post.entities.Post;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
public class PostListResponseElement {
    private String postId;
    private String title;
    private String user;
    private String createdAt;
    private boolean recent;
    private long likeCount;
    private long commentCount;
    private long viewCount;

    public PostListResponseElement(Post post, Long viewCount, Long commentCount, Long likeCount){
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.user = post.getUser().getEmail();
        this.createdAt = post.getCreatedAt().toString();
        this.recent = isRecent(post.getCreatedAt());
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
    }

    public PostListResponseElement(String postId, String title, String user, LocalDateTime createdAt, long viewCount, long commentCount, long likeCount){
        this.postId = postId;
        this.title = title;
        this.user = user;
        this.createdAt = createdAt.toString();
        this.recent = isRecent(createdAt);
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
    }

    private static boolean isRecent(LocalDateTime createdAt){
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(createdAt, now);
        return duration.toDays() <= 3;
    }
}
