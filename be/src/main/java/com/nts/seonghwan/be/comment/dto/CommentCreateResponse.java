package com.nts.seonghwan.be.comment.dto;

import com.nts.seonghwan.be.comment.entities.Comment;
import com.nts.seonghwan.be.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CommentCreateResponse extends CommentResponse{

    public CommentCreateResponse(Long commentId, String user, String content, String createdAt, boolean isDeleted, boolean ableToDelete) {
        super(commentId, user, content, createdAt, isDeleted, ableToDelete);
    }

    public static CommentCreateResponse from(Comment comment, User user){
        return new CommentCreateResponse(
                comment.getId(),
                user.getEmail(),
                comment.getContent(),
                comment.getCreatedAt().toString(),
                comment.isDeleted(),
                comment.validateAbleToDelete(user));
    }
}
