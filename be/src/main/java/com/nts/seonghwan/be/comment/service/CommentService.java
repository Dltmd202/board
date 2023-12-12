package com.nts.seonghwan.be.comment.service;

import com.nts.seonghwan.be.comment.dto.CommentCreateRequest;
import com.nts.seonghwan.be.comment.dto.CommentCreateResponse;
import com.nts.seonghwan.be.comment.entities.Comment;
import com.nts.seonghwan.be.comment.exception.InvalidCommenterException;
import com.nts.seonghwan.be.comment.repository.CommentRepository;
import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.post.exception.NotFoundPostException;
import com.nts.seonghwan.be.post.repository.PostRepository;
import com.nts.seonghwan.be.user.entities.User;
import com.nts.seonghwan.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    private User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(InvalidCommenterException::new);
    }

    private Post getPostById(String postId){
        return postRepository.findByIdWithUser(postId)
                .orElseThrow(NotFoundPostException::new);
    }
}
