package com.nts.seonghwan.be.preference.repository;

import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.preference.entities.Preference;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import com.nts.seonghwan.be.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    Optional<Preference> findByUserAndPostAndType(User user, Post post, PreferenceType type);

    Long countByPostAndTypeAndDeletedAtIsNull(Post post, PreferenceType type);
}
