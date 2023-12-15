package com.nts.seonghwan.be.post.entities.id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class PostTagId implements Serializable {
    private Long post;
    private String tag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTagId postTagId = (PostTagId) o;
        return Objects.equals(post, postTagId.post) && Objects.equals(tag, postTagId.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post, tag);
    }
}
