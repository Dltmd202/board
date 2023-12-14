package com.nts.seonghwan.be.preference.repository;

import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.preference.dto.PreferenceDto;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import com.nts.seonghwan.be.user.entities.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class PreferenceCustomRepositoryImpl implements PreferenceCustomRepository{
    private final EntityManager em;

    @Override
    public PreferenceDto findPreferenceDtoByPostIdAndUserId(Post post, User user, PreferenceType type) {
        List<PreferenceDto> resultList = em.createQuery(
                    "select new com.nts.seonghwan.be.preference.dto.PreferenceDto(" +
                            "(" +
                                "select p from Preference p where p.post = :post and p.user = :user and p.type = :type)," +
                                "p.type," +
                                "count(p.id)" +
                            ") " +
                            "from Preference p " +
                            "where p.post = :post " +
                            "and p.type = :type " +
                            "group by p.post ",
                    PreferenceDto.class)
            .setParameter("user", user)
            .setParameter("post", post)
            .setParameter("type", type)
            .getResultList();

        if(resultList.isEmpty()) return PreferenceDto.defaultPreference(type);
        return resultList.get(0);
    }

}
