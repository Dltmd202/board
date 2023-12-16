package com.nts.seonghwan.be.post.repository;

import com.nts.seonghwan.be.post.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
}
