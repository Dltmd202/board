package com.nts.seonghwan.be.post.entities;

import com.nts.seonghwan.be.post.entities.id.PostViewId;
import com.nts.seonghwan.be.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@IdClass(PostViewId.class)
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PostView {
    @Id @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Id @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDate createdAt;

    public PostView(Post post, User user) {
        this.post = post;
        this.user = user;
        createdAt = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostView postView = (PostView) o;
        return Objects.equals(post.getId(), postView.post.getId())
                && Objects.equals(user.getId(), postView.user.getId())
                && Objects.equals(createdAt, postView.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post, user, createdAt);
    }
}
