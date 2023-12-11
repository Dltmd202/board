package com.nts.seonghwan.be.post.entities;

import com.nts.seonghwan.be.mock.StubUUIDHolder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PostTest {
    private StubUUIDHolder uuidHolder = new StubUUIDHolder();

    @Test
    void grantPostId는_postId를_부여한다() {
        // given
        Post post = new Post();

        // when
        uuidHolder.setUUID("abcd-efgh-ijkl");
        post.grantPostId(uuidHolder);

        // then
        Assertions.assertThat(post.getPostId()).isEqualTo("abcd-efgh-ijkl");
    }
}