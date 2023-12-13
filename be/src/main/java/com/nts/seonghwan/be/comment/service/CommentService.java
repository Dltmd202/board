package com.nts.seonghwan.be.comment.service;

import com.nts.seonghwan.be.comment.dto.CommentCreateRequest;
import com.nts.seonghwan.be.comment.dto.CommentCreateResponse;
import com.nts.seonghwan.be.comment.dto.CommentResponse;
import com.nts.seonghwan.be.comment.entities.Comment;
import com.nts.seonghwan.be.comment.exception.CommentAlreadyDeletedException;
import com.nts.seonghwan.be.comment.exception.InvalidCommenterException;
import com.nts.seonghwan.be.comment.exception.NotFoundCommentException;
import com.nts.seonghwan.be.comment.repository.CommentRepository;
import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.post.exception.NotFoundPostException;
import com.nts.seonghwan.be.post.repository.PostRepository;
import com.nts.seonghwan.be.user.entities.User;
import com.nts.seonghwan.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional()
    public CommentCreateResponse createComment(
            CommentCreateRequest commentCreateRequest, Long userId, String postId){
        User commenter = getUserById(userId);
        Post post = getPostById(postId);
        Comment comment = commentCreateRequest.toEntity(commenter, post);
        Comment savedComment = commentRepository.save(comment);
        return CommentCreateResponse.from(savedComment, commenter);
    }

    @Transactional(readOnly = true)
    public Page<CommentResponse> getComments(String postId, Long userId, Pageable pageable) {
        Post post = getPostById(postId);
        return commentRepository.findAllByPost(post, pageable)
                .map(c -> CommentResponse.from(c, userId));
    }

    @Transactional
    public void deleteComment(String postId, Long commentId, Long userId) {
        Comment comment = getCommentById(postId, commentId);
        validateUserComment(comment, userId);
        validateAlreadyDeletedComment(comment);

        comment.delete();
    }

    private Comment getCommentById(String postId, Long commentId) {
        return commentRepository.findByIdAndPostPostId(commentId, postId)
                .orElseThrow(NotFoundCommentException::new);
    }

    private User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(InvalidCommenterException::new);
    }

    private Post getPostById(String postId){
        return postRepository.findByIdWithUser(postId)
                .orElseThrow(NotFoundPostException::new);
    }

    private void validateUserComment(Comment comment, Long userId) {
        if (!comment.getUser().getId().equals(userId)) throw new InvalidCommenterException();
    }

    private void validateAlreadyDeletedComment(Comment comment) {
        if (comment.isDeleted()) throw new CommentAlreadyDeletedException();
    }
}
