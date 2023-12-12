package com.nts.seonghwan.be.comment.dto;

import com.nts.seonghwan.be.comment.entities.Comment;
import com.nts.seonghwan.be.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class CommentCreateResponse {
    private Long commentId;
    private String user;
    private String content;
    private String createdAt;

    public static CommentCreateResponse from(Comment comment, User user){
        return new CommentCreateResponse(
                comment.getId(),
                user.getEmail(),
                comment.getContent(),
                comment.getCreatedAt().toString());
    }
}
