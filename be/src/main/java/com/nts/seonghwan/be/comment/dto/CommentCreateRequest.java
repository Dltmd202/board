package com.nts.seonghwan.be.comment.dto;

import com.nts.seonghwan.be.comment.entities.Comment;
import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequest {

    @Length(max = 255, message = "댓글을 255자를 넘을 수 없습니다.")
    @NotBlank(message = "댓글을 입력해주세요.")
    private String content;

    @NotBlank(message = "게시글을 선택해주세요.")
    private String postId;

    public Comment toEntity(User user, Post post){
        return Comment.builder()
                .user(user)
                .post(post)
                .content(content)
                .build();
    }
}
