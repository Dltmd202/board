package com.nts.seonghwan.be.comment.service;

import com.nts.seonghwan.be.comment.dto.CommentCreateRequest;
import com.nts.seonghwan.be.comment.dto.CommentCreateResponse;
import com.nts.seonghwan.be.comment.exception.InvalidCommenterException;
import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.post.exception.NotFoundPostException;
import com.nts.seonghwan.be.service.ServiceTest;
import com.nts.seonghwan.be.user.entities.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CommentServiceTest extends ServiceTest {

    @Test
    void createComment는_댓글을_생성할_수_있다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        Post post = getDefaultPost("제목", "내용", user);
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest("댓글~", post.getPostId());

        // when
        CommentCreateResponse comment = commentService.createComment(commentCreateRequest, user.getId());

        // then
        assertThat(comment.getContent()).isEqualTo("댓글~");
        assertThat(comment.getUser()).isEqualTo("abc123@abc.com");
        assertThat(comment.getCreatedAt()).isNotNull();
        assertThat(comment.getCommentId()).isNotNull();
    }

    @Test
    void createComment는_존재하지_않는_게시글에_댓글을_생성할_수_없다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        Post post = getDefaultPost("제목", "내용", user);
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest("댓글~", post.getPostId() + "1");

        // when
        assertThatThrownBy(
                () -> commentService.createComment(commentCreateRequest, user.getId())
        ).isInstanceOf(NotFoundPostException.class);
    }

    @Test
    void createComment는_존재하지_않는_사용자가_댓글을_생성할_수_없다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        Post post = getDefaultPost("제목", "내용", user);
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest("댓글~", "존재하지 않는 게시글");

        // when
        assertThatThrownBy(
                () -> commentService.createComment(commentCreateRequest, -1L)
        ).isInstanceOf(InvalidCommenterException.class);
    }
}