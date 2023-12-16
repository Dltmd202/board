package com.nts.seonghwan.be.comment.repository;

import com.nts.seonghwan.be.comment.entities.Comment;
import com.nts.seonghwan.be.post.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPost(Post post, Pageable pageable);

    Optional<Comment> findByIdAndPostPostId(Long commentId, String postId);

    Long countByDeletedAtIsNull();
}
