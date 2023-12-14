package com.nts.seonghwan.be.post.entities.id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class PostViewId implements Serializable {
    private Long post;
    private Long user;
    private LocalDate createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostViewId that = (PostViewId) o;
        return Objects.equals(post, that.post) && Objects.equals(user, that.user) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post, user, createdAt);
    }
}
