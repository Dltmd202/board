package com.nts.seonghwan.be.post.service;

import com.nts.seonghwan.be.common.service.UUIDHolder;
import com.nts.seonghwan.be.post.dto.PostCreateRequest;
import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.post.exception.InvalidWriterException;
import com.nts.seonghwan.be.post.repository.PostRepository;
import com.nts.seonghwan.be.user.entities.User;
import com.nts.seonghwan.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UUIDHolder uuidHolder;

    @Transactional()
    public void savePost(PostCreateRequest postCreate, Long userId) {
        User writer = getUserById(userId);
        Post post = postCreate.toEntity(writer);
        post.grantPostId(uuidHolder);
        Post savePost = postRepository.save(post);
    }

    private User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(InvalidWriterException::new);
    }
}
