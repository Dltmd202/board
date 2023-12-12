package com.nts.seonghwan.be.comment.dto;

import com.nts.seonghwan.be.comment.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String user;
    private String content;
    private String createdAt;

    public static CommentResponse from(Comment comment){
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getEmail(),
                comment.getContent(),
                comment.getCreatedAt().toString());
    }
}
