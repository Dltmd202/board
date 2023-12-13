package com.nts.seonghwan.be.comment.service;

import com.nts.seonghwan.be.comment.dto.CommentCreateRequest;
import com.nts.seonghwan.be.comment.dto.CommentCreateResponse;
import com.nts.seonghwan.be.comment.entities.Comment;
import com.nts.seonghwan.be.comment.exception.CommentAlreadyDeletedException;
import com.nts.seonghwan.be.comment.exception.InvalidCommenterException;
import com.nts.seonghwan.be.comment.exception.NotFoundCommentException;
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
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest("댓글~");

        // when
        CommentCreateResponse comment = commentService.createComment(
                commentCreateRequest, user.getId(), post.getPostId());

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
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest("댓글~");

        // when
        assertThatThrownBy(
                () -> commentService.createComment(
                        commentCreateRequest, user.getId(), post.getPostId() + "1")
        ).isInstanceOf(NotFoundPostException.class);
    }

    @Test
    void createComment는_존재하지_않는_사용자가_댓글을_생성할_수_없다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        Post post = getDefaultPost("제목", "내용", user);
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest("댓글~");

        // when
        assertThatThrownBy(
                () -> commentService.createComment(commentCreateRequest, -1L, post.getPostId())
        ).isInstanceOf(InvalidCommenterException.class);
    }

    @Test
    void deleteComment는_댓글을_삭제할_수_있다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        Post post = getDefaultPost("제목", "내용", user);
        Comment comment = getDefaultComment("댓글~", user, post);

        // when
        commentService.deleteComment(post.getPostId(), comment.getId(), user.getId());

        // then
        assertThat(comment.isDeleted()).isTrue();
    }

    @Test
    void deleteComment는_존재하지_않는_게시글의_댓글을_삭제할_수_없다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        Post post = getDefaultPost("제목", "내용", user);
        Comment comment = getDefaultComment("댓글~", user, post);

        // when
        // then
        assertThatThrownBy(
                () -> commentService.deleteComment(post.getPostId() + "1", comment.getId(), user.getId())
        ).isInstanceOf(NotFoundCommentException.class);
    }

    @Test
    void deleteComment는_이미_삭제된_댓글은_삭제하지_못_한다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        Post post = getDefaultPost("제목", "내용", user);
        Comment comment = getDefaultComment("댓글~", user, post);
        comment.delete();

        // when
        // then
        assertThatThrownBy(
                () -> commentService.deleteComment(post.getPostId(), comment.getId(), user.getId())
        ).isInstanceOf(CommentAlreadyDeletedException.class);
    }

    @Test
    void deleteComment는_본인이_작성한_댓글이_아니면_삭제하지_못_한다() {
        // given
        User user = getDefaultUser("abc123@abc.com", "1234");
        Post post = getDefaultPost("제목", "내용", user);
        Comment comment = getDefaultComment("댓글~", user, post);

        // when
        // then
        assertThatThrownBy(
                () -> commentService.deleteComment(post.getPostId(), comment.getId(), user.getId() + 1)
        ).isInstanceOf(InvalidCommenterException.class);
    }
}