package com.nts.seonghwan.be.post.dto;

import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class PostCreateRequest {

    @Length(max = 100, message = "제목을 확인해주세요.")
    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    private String content;

    public Post toEntity(User writer){
        return Post.builder()
                .title(title)
                .content(content)
                .user(writer)
                .build();
    }
}
