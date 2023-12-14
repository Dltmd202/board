package com.nts.seonghwan.be.preference.service;

import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.post.exception.NotFoundPostException;
import com.nts.seonghwan.be.post.repository.PostRepository;
import com.nts.seonghwan.be.preference.dto.PreferenceToggleResponse;
import com.nts.seonghwan.be.preference.entities.Preference;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import com.nts.seonghwan.be.preference.exception.UnAuthorizedPreferenceException;
import com.nts.seonghwan.be.preference.repository.PreferenceRepository;
import com.nts.seonghwan.be.user.entities.User;
import com.nts.seonghwan.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PreferenceService {
    private final PreferenceRepository preferenceRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional()
    public PreferenceToggleResponse togglePreference(Long userId, String postId, PreferenceType type) {
        User user = getUserById(userId);
        Post post = getPostById(postId);
        Preference preference = this.getPreferenceById(user, post, type);

        preference.toggle();
        Long count = this.preferenceRepository.countByPostAndTypeAndDeletedAtIsNull(post, type);
        return PreferenceToggleResponse.from(preference, type, count);
    }

    private User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(UnAuthorizedPreferenceException::new);
    }

    private Post getPostById(String postId){
        return postRepository.findByPostId(postId)
                .orElseThrow(NotFoundPostException::new);
    }

    private Preference getPreferenceById(User user, Post post, PreferenceType type){
        return preferenceRepository.findByUserAndPostAndType(user, post, type)
                .orElseGet(() -> preferenceRepository.save(new Preference(user, post, type)));
    }
}
