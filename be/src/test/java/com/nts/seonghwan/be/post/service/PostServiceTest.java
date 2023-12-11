package com.nts.seonghwan.be.post.service;

import com.nts.seonghwan.be.post.dto.PostCreateRequest;
import com.nts.seonghwan.be.post.dto.PostCreateResponse;
import com.nts.seonghwan.be.post.exception.InvalidWriterException;
import com.nts.seonghwan.be.service.ServiceTest;
import com.nts.seonghwan.be.user.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PostServiceTest extends ServiceTest {
    @Test
    void savePost는_게시글을_저장한다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        PostCreateRequest postCreateRequest = new PostCreateRequest("title", "content");

        // when
        PostCreateResponse postCreateResponse = postService.savePost(postCreateRequest, user.getId());

        // then
        assertThat(postCreateResponse.getTitle()).isEqualTo("title");
        assertThat(postCreateResponse.getContent()).isEqualTo("content");
    }

    @Test
    void savePost는_게시글을_저장하면_게시글_아이디를_반환한다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        PostCreateRequest postCreateRequest = new PostCreateRequest("title", "content");

        // when
        // then
        Assertions.assertThatThrownBy(
                () -> postService.savePost(postCreateRequest, user.getId())
        ).isInstanceOf(InvalidWriterException.class);
    }
}