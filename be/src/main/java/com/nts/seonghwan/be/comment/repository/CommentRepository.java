package com.nts.seonghwan.be.comment.repository;

import com.nts.seonghwan.be.comment.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
