package com.nts.seonghwan.be.post.entities;

import com.nts.seonghwan.be.common.service.UUIDHolder;
import com.nts.seonghwan.be.tag.entities.Tag;
import com.nts.seonghwan.be.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String postId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostTag> tags;

    public void grantPostId(UUIDHolder uuidHolder){
        this.postId = uuidHolder.uuid();
    }

    public void tag(List<PostTag> tags){
        this.tags = tags;
    }

    public List<String> getTag(){
        return tags.stream()
                .map(PostTag::getTag)
                .map(Tag::getName)
                .toList();
    }
}
