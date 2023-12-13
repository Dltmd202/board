package com.nts.seonghwan.be.tag.repository;

import com.nts.seonghwan.be.tag.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
}
