package com.nts.seonghwan.be.comment.dto;

import com.nts.seonghwan.be.comment.entities.Comment;
import com.nts.seonghwan.be.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String user;
    private String content;
    private String createdAt;
    private boolean isDeleted;
    private boolean ableToDelete;

    public static CommentResponse from(Comment comment, User user){
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getEmail(),
                comment.getContent(),
                comment.getCreatedAt().toString(),
                comment.isDeleted(),
                comment.validateAbleToDelete(user));
    }

    public static CommentResponse from(Comment comment, Long userId){
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getEmail(),
                comment.getContent(),
                comment.getCreatedAt().toString(),
                comment.isDeleted(),
                comment.validateAbleToDelete(userId));
    }
}
