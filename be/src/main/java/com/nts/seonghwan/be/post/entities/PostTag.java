package com.nts.seonghwan.be.post.entities;

import com.nts.seonghwan.be.post.entities.id.PostTagId;
import com.nts.seonghwan.be.tag.entities.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@IdClass(PostTagId.class)
@NoArgsConstructor(access = PROTECTED)
public class PostTag {

    @Id @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Id @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;
}
