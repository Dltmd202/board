package com.nts.seonghwan.be.post.repository;

import com.nts.seonghwan.be.post.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
