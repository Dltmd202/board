package com.nts.seonghwan.be.comment.entities;

import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.user.entities.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
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
}
