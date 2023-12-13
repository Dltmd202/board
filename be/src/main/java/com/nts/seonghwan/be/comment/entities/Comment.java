package com.nts.seonghwan.be.comment.entities;

import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.user.entities.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column()
    private LocalDateTime deletedAt;

    public String getContent() {
        if(this.isDeleted()) return "삭제된 댓글입니다.";
        return this.content;
    }

    public boolean validateCommentOwner(User user) {
        return this.validateCommentOwner(user.getId());
    }

    public boolean validateCommentOwner(Long userId) {
        return this.user.getId().equals(userId);
    }

    public boolean validateAbleToDelete(Long userId){
        return this.validateCommentOwner(userId) && !this.isDeleted();
    }

    public boolean validateAbleToDelete(User user){
        return this.validateCommentOwner(user) && !this.isDeleted();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public boolean isDeleted() {
        return this.deletedAt != null;
    }
}
