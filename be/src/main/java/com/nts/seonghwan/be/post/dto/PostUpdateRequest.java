package com.nts.seonghwan.be.post.dto;

import com.nts.seonghwan.be.post.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

import static lombok.AccessLevel.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class PostUpdateRequest {
    @Length(max = 100, message = "제목을 확인해주세요.")
    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    private String content;

    @Size(max = 5, message = "태그는 최대 5개까지만 입력할 수 있습니다.")
    private List<String> tag;

    public void update(Post post){
        post.update(title, content);
    }
}
