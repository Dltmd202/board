package com.nts.seonghwan.be.post.repository;

import com.nts.seonghwan.be.post.dto.PostListResponseElement;
import com.nts.seonghwan.be.post.dto.PostSearch;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
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
    public Page<PostListResponseElement> findPostListByUserId(Pageable pageable, PostSearch postSearch) {
        String jpql = "select new com.nts.seonghwan.be.post.dto.PostListResponseElement(" +
                        "p.postId, p.title, p.user.email, p.createdAt, " +
                        "(select count(*) from PostView pv where pv.post = p), " +
                        "(select count(*) from Comment c where c.post = p and c.deletedAt is null), " +
                        "(select count(*) from Preference pr where pr.post.postId = p.postId and pr.type = :like and pr.deletedAt is null) " +
                    ") " +
                    "from Post p ";

        if(postSearch.getWriter() != null) jpql += "join p.user u ";
        if(postSearch.getTag() != null) jpql += "left join p.tags pt join pt.tag t ";

        jpql += "where 1=1 ";
        if(postSearch.getTitle() != null) jpql += "and p.title like concat('%', :title, '%') ";
        if(postSearch.getWriter() != null) jpql += "and p.user.email like concat('%', :writer, '%') ";
        if(postSearch.getContent() != null) jpql += "and p.content like concat('%', :content, '%') ";
        if(postSearch.getTag() != null) jpql += "and t.name like :tag ";
        jpql += "order by p.id desc";

        TypedQuery<PostListResponseElement> query = em.createQuery(jpql, PostListResponseElement.class);

        if(postSearch.getTitle() != null) query.setParameter("title", postSearch.getTitle());
        if(postSearch.getWriter() != null) query.setParameter("writer", postSearch.getWriter());
        if(postSearch.getContent() != null) query.setParameter("content", postSearch.getContent());
        if(postSearch.getTag() != null) query.setParameter("tag", postSearch.getTag());

        query.setParameter("like", PreferenceType.LIKE);
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
