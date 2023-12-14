package com.nts.seonghwan.be.preference.repository;

import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.preference.dto.PreferenceDto;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import com.nts.seonghwan.be.user.entities.User;

public interface PreferenceCustomRepository {
    PreferenceDto findPreferenceDtoByPostIdAndUserId(Post post, User user, PreferenceType type);
}
