package com.nts.seonghwan.be.post.service;

import com.nts.seonghwan.be.common.service.UUIDHolder;
import com.nts.seonghwan.be.post.dto.PostCreateRequest;
import com.nts.seonghwan.be.post.dto.PostCreateResponse;
import com.nts.seonghwan.be.post.dto.PostDetailResponse;
import com.nts.seonghwan.be.post.dto.PostListResponse;
import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.post.entities.PostTag;
import com.nts.seonghwan.be.post.entities.PostView;
import com.nts.seonghwan.be.post.exception.InvalidWriterException;
import com.nts.seonghwan.be.post.exception.NotFoundPostException;
import com.nts.seonghwan.be.post.repository.PostRepository;
import com.nts.seonghwan.be.post.repository.PostViewRepository;
import com.nts.seonghwan.be.preference.dto.PreferenceDto;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import com.nts.seonghwan.be.preference.repository.PreferenceRepository;
import com.nts.seonghwan.be.tag.entities.Tag;
import com.nts.seonghwan.be.tag.repository.TagRepository;
import com.nts.seonghwan.be.user.entities.User;
import com.nts.seonghwan.be.user.exception.UserNotFoundException;
import com.nts.seonghwan.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UUIDHolder uuidHolder;
    private final TagRepository tagRepository;
    private final PostViewRepository postViewRepository;
    private final PreferenceRepository preferenceRepository;

    @Transactional()
    public PostCreateResponse savePost(PostCreateRequest postCreate, Long userId) {
        User writer = getWriterById(userId);
        Post post = postCreate.toEntity(writer);

        post.grantPostId(uuidHolder);
        post.tag(
                postCreate.getTag().stream()
                    .map(this::getOrDefault)
                    .map(pt -> new PostTag(post, pt))
                    .toList());

        Post savePost = postRepository.save(post);
        return PostCreateResponse.from(savePost);
    }

    @Transactional(readOnly = true)
    public Page<PostListResponse> findPost(Pageable pageable){
        return postRepository.findByOrderByIdDesc(pageable).map(PostListResponse::new);
    }

    @Transactional()
    public PostDetailResponse getPost(String postId, Long userId) {
        Post post = getPostById(postId);
        User user = getUserById(userId);

        PostView view = post.view(user);
        postViewRepository.save(view);

        Long viewCount = postViewRepository.countByPostPostId(postId);
        PreferenceDto like = preferenceRepository.findPreferenceDtoByPostIdAndUserId(post, user, PreferenceType.LIKE);
        PreferenceDto unlike = preferenceRepository.findPreferenceDtoByPostIdAndUserId(post, user, PreferenceType.LIKE);

        return new PostDetailResponse(post, viewCount, like, unlike);
    }

    private User getWriterById(Long id){
        return userRepository.findById(id)
                .orElseThrow(InvalidWriterException::new);
    }

    private User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    private Post getPostById(String postId){
        return postRepository.findByIdWithUser(postId)
                .orElseThrow(NotFoundPostException::new);
    }

    private Tag getOrDefault(String tag){
        return tagRepository.findById(tag)
                .orElseGet(() -> tagRepository.save(new Tag(tag)));
    }

}
