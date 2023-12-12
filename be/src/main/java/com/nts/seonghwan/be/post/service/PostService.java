package com.nts.seonghwan.be.post.service;

import com.nts.seonghwan.be.common.service.UUIDHolder;
import com.nts.seonghwan.be.post.dto.PostCreateRequest;
import com.nts.seonghwan.be.post.dto.PostCreateResponse;
import com.nts.seonghwan.be.post.dto.PostDetailResponse;
import com.nts.seonghwan.be.post.dto.PostListResponse;
import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.post.exception.InvalidWriterException;
import com.nts.seonghwan.be.post.exception.NotFoundPostException;
import com.nts.seonghwan.be.post.repository.PostRepository;
import com.nts.seonghwan.be.user.entities.User;
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

    @Transactional()
    public PostCreateResponse savePost(PostCreateRequest postCreate, Long userId) {
        User writer = getUserById(userId);
        Post post = postCreate.toEntity(writer);
        post.grantPostId(uuidHolder);
        Post savePost = postRepository.save(post);
        return PostCreateResponse.from(savePost);
    }

    @Transactional(readOnly = true)
    public Page<PostListResponse> findPost(Pageable pageable){
        return postRepository.findAll(pageable).map(PostListResponse::new);
    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPost(String postId) {
        Post post = getPostById(postId);
        return new PostDetailResponse(post);
    }

    private User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(InvalidWriterException::new);
    }

    private Post getPostById(String postId){
        return postRepository.findByIdWithUser(postId)
                .orElseThrow(NotFoundPostException::new);
    }

}
