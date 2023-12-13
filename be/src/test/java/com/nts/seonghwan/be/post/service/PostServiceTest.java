package com.nts.seonghwan.be.post.service;

import com.nts.seonghwan.be.post.dto.PostCreateRequest;
import com.nts.seonghwan.be.post.dto.PostCreateResponse;
import com.nts.seonghwan.be.post.dto.PostDetailResponse;
import com.nts.seonghwan.be.post.dto.PostListResponse;
import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.post.exception.InvalidWriterException;
import com.nts.seonghwan.be.post.exception.NotFoundPostException;
import com.nts.seonghwan.be.service.ServiceTest;
import com.nts.seonghwan.be.user.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PostServiceTest extends ServiceTest {
    @Test
    void savePost는_게시글을_저장한다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        PostCreateRequest postCreateRequest = new PostCreateRequest("title", "content", new ArrayList<>());

        // when
        PostCreateResponse postCreateResponse = postService.savePost(postCreateRequest, user.getId());

        // then
        assertThat(postCreateResponse.getTitle()).isEqualTo("title");
        assertThat(postCreateResponse.getContent()).isEqualTo("content");
    }

    @Test
    void savePost는_잘못된_사용자로_요청할_수_없다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        PostCreateRequest postCreateRequest = new PostCreateRequest("title", "content", new ArrayList<>());

        // when
        // then
        Assertions.assertThatThrownBy(
                () -> postService.savePost(postCreateRequest, user.getId() + 1)
        ).isInstanceOf(InvalidWriterException.class);
    }

    @Test
    void savePost는_게시글에_태그를_부여할_수_있다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        List<String> tag = Arrays.asList("tag1", "tag2");
        PostCreateRequest postCreateRequest = new PostCreateRequest("title", "content", tag);

        // when
        PostCreateResponse postCreateResponse = postService.savePost(postCreateRequest, user.getId());

        // then
        assertThat(postCreateResponse.getTitle()).isEqualTo("title");
        assertThat(postCreateResponse.getContent()).isEqualTo("content");
        assertThat(postCreateResponse.getTag()).isEqualTo(tag);
    }

    @Test
    void findPost는_게시글을_조회한다() {
        // given
        User user1 = getDefaultUser("abc123@abc.com", "1234");
        User user2 = getDefaultUser("abc124@abc.com", "1234");
        getDefaultPost("title1", "content1", user1);
        getDefaultPost("title2", "content2", user2);
        getDefaultPost("title3", "content3", user2);
        getDefaultPost("title4", "content4", user2);

        // when
        Page<PostListResponse> post = postService.findPost(Pageable.unpaged());

        // then
        assertThat(post.getTotalElements()).isEqualTo(4);
        assertThat(post.getContent().get(0).getTitle()).isEqualTo("title1");
        assertThat(post.getContent().get(0).getUser()).isEqualTo("abc123@abc.com");
        assertThat(post.getContent().get(3).getTitle()).isEqualTo("title4");
        assertThat(post.getContent().get(3).getUser()).isEqualTo("abc124@abc.com");
    }

    @Test
    void findPost는_게시글이_없어도_호출할_수_있다() {
        // given
        // when
        Page<PostListResponse> post = postService.findPost(Pageable.unpaged());

        // then
        assertThat(post.getTotalElements()).isEqualTo(0);
    }

    @Test
    void getPost는_게시글을_조회한다() {
        // given
        User user = getDefaultUser("def123@abc.com", "1234");
        Post post = getDefaultPost("title", "content", user);

        // when
        PostDetailResponse postDetail = postService.getPost(post.getPostId());

        // then
        assertThat(postDetail.getTitle()).isEqualTo("title");
        assertThat(postDetail.getContent()).isEqualTo("content");
        assertThat(postDetail.getUser()).isEqualTo("def123@abc.com");
        assertThat(postDetail.getCreatedAt()).isNotNull();
    }

    @Test
    void getPost는_잚못된_아이디의_게시글은_조회하지_못_한다() {
        // given
        User user = getDefaultUser("def123@abc.com", "1234");
        Post post = getDefaultPost("title", "content", user);

        // when
        // then
        assertThatThrownBy(
                () -> postService.getPost("abc")
        ).isInstanceOf(NotFoundPostException.class);
    }
}