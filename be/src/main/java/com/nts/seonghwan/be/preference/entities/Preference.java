package com.nts.seonghwan.be.preference.entities;

import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.user.entities.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Preference {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, referencedColumnName = "postId")
    private Post post;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private PreferenceType type;

    @Column()
    private LocalDateTime deletedAt;

    public void toggle(){
        if(this.isDeleted()) this.restore();
        else this.delete();
    }

    public boolean isDeleted(){
        return this.deletedAt != null;
    }

    public void restore(){
        this.deletedAt = null;
    }

    public void delete(){
        this.deletedAt = LocalDateTime.now();
    }

    public Preference(User user, Post post, PreferenceType type) {
        this.user = user;
        this.post = post;
        this.type = type;
        this.delete();
    }
}
