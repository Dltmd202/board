package com.nts.seonghwan.be.preference.service;

import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.post.exception.NotFoundPostException;
import com.nts.seonghwan.be.preference.dto.PreferenceResponse;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import com.nts.seonghwan.be.preference.exception.UnAuthorizedPreferenceException;
import com.nts.seonghwan.be.service.ServiceTest;
import com.nts.seonghwan.be.user.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PreferenceServiceTest extends ServiceTest {

    @Test
    void savePreference는_게시글에_좋아요를_할_수_있다() {
        // given
        User user = getDefaultUser("abc123@gmail.com", "1234");
        Post post = getDefaultPost("title", "content", user);

        // when
        PreferenceResponse preferenceResponse =
                preferenceService.togglePreference(user.getId(), post.getPostId(), PreferenceType.LIKE);

        // then
        assertThat(preferenceResponse.isAbleToPreference()).isEqualTo(false);
        assertThat(preferenceResponse.getType()).isEqualTo(PreferenceType.LIKE);
        assertThat(preferenceResponse.getCount()).isEqualTo(1L);
    }

    @Test
    void savePreference는_게시글에_좋아요를_취소할_수_있다() {
        // given
        User user = getDefaultUser("abc123@gmail.com", "1234");
        Post post = getDefaultPost("title", "content", user);
        getDefaultPreference(user, post, PreferenceType.LIKE);

        // when
        PreferenceResponse preferenceResponse =
                preferenceService.togglePreference(user.getId(), post.getPostId(), PreferenceType.LIKE);

        // then
        assertThat(preferenceResponse.isAbleToPreference()).isEqualTo(true);
        assertThat(preferenceResponse.getType()).isEqualTo(PreferenceType.LIKE);
        assertThat(preferenceResponse.getCount()).isEqualTo(0L);
    }

    @Test
    void savePreference는_게시글에_싫어요를_할_수_있다() {
        // given
        User user = getDefaultUser("abc123@gmail.com", "1234");
        Post post = getDefaultPost("title", "content", user);

        // when
        PreferenceResponse preferenceResponse =
                preferenceService.togglePreference(user.getId(), post.getPostId(), PreferenceType.UNLIKE);

        // then
        assertThat(preferenceResponse.isAbleToPreference()).isEqualTo(false);
        assertThat(preferenceResponse.getType()).isEqualTo(PreferenceType.UNLIKE);
        assertThat(preferenceResponse.getCount()).isEqualTo(1L);
    }

    @Test
    void savePreference는_게시글에_싫어요를_취소할_수_있다() {
        // given
        User user = getDefaultUser("abc123@gmail.com", "1234");
        Post post = getDefaultPost("title", "content", user);
        getDefaultPreference(user, post, PreferenceType.UNLIKE);

        // when
        PreferenceResponse preferenceResponse =
                preferenceService.togglePreference(user.getId(), post.getPostId(), PreferenceType.UNLIKE);

        // then
        assertThat(preferenceResponse.isAbleToPreference()).isEqualTo(true);
        assertThat(preferenceResponse.getType()).isEqualTo(PreferenceType.UNLIKE);
        assertThat(preferenceResponse.getCount()).isEqualTo(0L);
    }

    @Test
    void savePreference는_올바르지_않은_사용자는_선호를_남길_수_없다() {
        // given
        User user = getDefaultUser("abc123@gmail.com", "1234");
        Post post = getDefaultPost("title", "content", user);

        // when
        // then
        Assertions.assertThatThrownBy(
                () -> preferenceService.togglePreference(user.getId() + 1, post.getPostId(), PreferenceType.LIKE)
        ).isInstanceOf(UnAuthorizedPreferenceException.class);
    }

    @Test
    void savePreference는_올바르지_않은_게시글에_선호를_남길_수_없다() {
        // given
        User user = getDefaultUser("abc123@gmail.com", "1234");
        Post post = getDefaultPost("title", "content", user);

        // when
        // then
        Assertions.assertThatThrownBy(
                () -> preferenceService.togglePreference(user.getId(), post.getPostId() + 1, PreferenceType.LIKE)
        ).isInstanceOf(NotFoundPostException.class);
    }
}