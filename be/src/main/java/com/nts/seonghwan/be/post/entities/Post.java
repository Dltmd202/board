package com.nts.seonghwan.be.post.entities;

import com.nts.seonghwan.be.comment.entities.Comment;
import com.nts.seonghwan.be.common.service.UUIDHolder;
import com.nts.seonghwan.be.preference.entities.Preference;
import com.nts.seonghwan.be.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post implements Serializable{
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<PostTag> tags = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final Set<PostView> views = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final List<Preference> preferences = new ArrayList<>();

    public void grantPostId(UUIDHolder uuidHolder){
        this.postId = uuidHolder.uuid();
    }

    public void tag(List<Tag> tags){
        Set<PostTag> postTags = tags.stream()
                .map(tag -> new PostTag(this, tag))
                .collect(Collectors.toSet());
        Set<PostTag> difference = new HashSet<>(this.tags);

        postTags.forEach(difference::remove);
        this.tags.removeAll(difference);
        this.tags.addAll(postTags);
    }

    public void view(User user){
        PostView postView = new PostView(this, user);
        this.views.add(postView);
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public List<String> getTag(){
        return tags.stream()
                .map(PostTag::getTag)
                .map(Tag::getName)
                .toList();
    }
}
