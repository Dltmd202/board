package com.nts.seonghwan.be.post.repository;

import com.nts.seonghwan.be.post.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

    @Query("select p from Post p join fetch p.user where p.postId = :postId")
    Optional<Post> findByIdWithUser(String postId);

    Optional<Post> findByPostId(String postId);
}
