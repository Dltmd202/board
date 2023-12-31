package com.nts.seonghwan.be.post.service;

import com.nts.seonghwan.be.comment.repository.CommentRepository;
import com.nts.seonghwan.be.common.service.UUIDHolder;
import com.nts.seonghwan.be.post.dto.*;
import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.post.exception.InvalidWriterException;
import com.nts.seonghwan.be.post.exception.NotFoundPostException;
import com.nts.seonghwan.be.post.repository.PostRepository;
import com.nts.seonghwan.be.post.repository.PostViewRepository;
import com.nts.seonghwan.be.preference.dto.PreferenceDto;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import com.nts.seonghwan.be.preference.repository.PreferenceRepository;
import com.nts.seonghwan.be.post.entities.Tag;
import com.nts.seonghwan.be.post.repository.TagRepository;
import com.nts.seonghwan.be.user.entities.User;
import com.nts.seonghwan.be.user.exception.ForbiddenUserAccessException;
import com.nts.seonghwan.be.user.exception.UserNotFoundException;
import com.nts.seonghwan.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UUIDHolder uuidHolder;
    private final TagRepository tagRepository;
    private final PostViewRepository postViewRepository;
    private final PreferenceRepository preferenceRepository;
    private final CommentRepository commentRepository;

    @Transactional()
    public PostCreateResponse savePost(PostCreateRequest postCreate, Long userId) {
        User writer = getWriterById(userId);
        Post post = postCreate.toEntity(writer);

        post.grantPostId(uuidHolder);
        post.tag(postCreate.getTag().stream()
                .map(this::getOrDefault)
                .toList());

        Post savePost = postRepository.save(post);
        return PostCreateResponse.from(savePost);
    }

    @Transactional(readOnly = true)
    public PostListResponse findPost(Pageable pageable, PostSearch postSearch){
        Page<PostListResponseElement> posts = postRepository.findPostListByUserId(pageable, postSearch);
        long commentCount = commentRepository.countByDeletedAtIsNull();
        return new PostListResponse(posts, commentCount);
    }

    @Transactional()
    public PostDetailResponse getPost(String postId, Long userId) {
        Post post = getPostById(postId);
        User user = getUserById(userId);
        post.view(user);

        return buildPostDetail(postId, post, user);
    }

    @Transactional()
    public void deletePost(String postId, Long userId) {
        Post post = getPostById(postId);
        validatePostUser(post, userId);

        postRepository.delete(post);
    }

    @Transactional()
    public void updatePost(String postId, PostUpdateRequest postUpdateRequest, Long userId) {
        Post post = getPostById(postId);
        validatePostUser(post, userId);

        post.tag(postUpdateRequest.getTag().stream()
                        .map(this::getOrDefault)
                        .toList());

        post.update(postUpdateRequest.getTitle(), postUpdateRequest.getContent());
    }

    private PostDetailResponse buildPostDetail(String postId, Post post, User user) {
        Long viewCount = postViewRepository.countByPostPostId(postId);
        PreferenceDto like = preferenceRepository
                .findPreferenceDtoByPostIdAndUserId(post, user, PreferenceType.LIKE);
        PreferenceDto unlike = preferenceRepository
                .findPreferenceDtoByPostIdAndUserId(post, user, PreferenceType.UNLIKE);
        return new PostDetailResponse(post, viewCount, like, unlike, user);
    }

    private void validatePostUser(Post post, Long userId){
        if(!Objects.equals(post.getUser().getId(), userId))
            throw new ForbiddenUserAccessException();
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
