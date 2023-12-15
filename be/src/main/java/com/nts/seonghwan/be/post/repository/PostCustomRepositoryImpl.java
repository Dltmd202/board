package com.nts.seonghwan.be.post.repository;

import com.nts.seonghwan.be.post.dto.PostListResponseElement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {
    private final EntityManager em;

    @Override
    public Page<PostListResponseElement> findPostListByUserId(Pageable pageable) {
        String jpql = "select new com.nts.seonghwan.be.post.dto.PostListResponseElement(" +
                        "p.postId, p.title, p.user.email, p.createdAt, " +
                        "(select count(*) from PostView pv where pv.post = p), " +
                        "(select count(*) from Comment c where c.post = p and c.deletedAt is null), " +
                        "(select count(*) from Preference pr where pr.post = p and pr.type = 'LIKE') " +
                    ") " +
                    "from Post p " +
                        "join p.user " +
                        "order by p.id desc";

        TypedQuery<PostListResponseElement> query = em.createQuery(jpql, PostListResponseElement.class);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return new PageImpl<>(query.getResultList(), pageable, getTotalCount());
    }

    public long getTotalCount() {
        String countJpql = "select count(*) from Post p";
        Query query = em.createQuery(countJpql);
        return ((Long) query.getSingleResult());
    }
}
