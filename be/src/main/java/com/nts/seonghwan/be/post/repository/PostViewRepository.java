package com.nts.seonghwan.be.post.repository;

import com.nts.seonghwan.be.post.entities.PostView;
import com.nts.seonghwan.be.post.entities.id.PostViewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostViewRepository extends JpaRepository<PostView, PostViewId> {
}
