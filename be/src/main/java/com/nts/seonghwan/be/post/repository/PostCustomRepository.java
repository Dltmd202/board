package com.nts.seonghwan.be.post.repository;

import com.nts.seonghwan.be.post.dto.PostListResponseElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {
    Page<PostListResponseElement> findPostListByUserId(Pageable pageable);
}
