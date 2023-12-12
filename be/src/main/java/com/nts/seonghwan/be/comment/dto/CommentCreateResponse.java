package com.nts.seonghwan.be.comment.dto;

import com.nts.seonghwan.be.comment.entities.Comment;
import com.nts.seonghwan.be.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CommentCreateResponse extends CommentResponse{

    public CommentCreateResponse(Long id, String email, String content, String string) {
        super(id, email, content, string);
    }

    public static CommentCreateResponse from(Comment comment, User user){
        return new CommentCreateResponse(
                comment.getId(),
                user.getEmail(),
                comment.getContent(),
                comment.getCreatedAt().toString());
    }
}
