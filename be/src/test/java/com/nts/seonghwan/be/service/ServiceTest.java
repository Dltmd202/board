package com.nts.seonghwan.be.service;

import com.nts.seonghwan.be.comment.entities.Comment;
import com.nts.seonghwan.be.comment.service.CommentService;
import com.nts.seonghwan.be.post.entities.Post;
import com.nts.seonghwan.be.post.service.PostService;
import com.nts.seonghwan.be.preference.entities.Preference;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import com.nts.seonghwan.be.preference.service.PreferenceService;
import com.nts.seonghwan.be.user.entities.User;
import com.nts.seonghwan.be.user.service.PasswordEncoder;
import com.nts.seonghwan.be.user.service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.UUID;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class ServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public PostService postService;

    @Autowired
    public CommentService commentService;

    @Autowired
    public PreferenceService preferenceService;

    @Autowired
    public EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getDefaultUser(String email, String password){
        User prevUser = User.builder()
                .email(email)
                .password(password)
                .build();
        prevUser.signup(passwordEncoder);
        em.persist(prevUser);
        return prevUser;
    }

    public Post getDefaultPost(String title, String content, User user){
        Post prevPost = Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .postId(UUID.randomUUID().toString())
                .build();
        em.persist(prevPost);
        return prevPost;
    }

    public Comment getDefaultComment(String content, User user, Post post){
        Comment prevComment = Comment.builder()
                .content(content)
                .user(user)
                .post(post)
                .build();
        em.persist(prevComment);
        return prevComment;
    }

    public Preference getDefaultPreference(User user, Post post, PreferenceType type){
        Preference prevPreference = Preference.builder()
                .user(user)
                .post(post)
                .type(type)
                .build();
        em.persist(prevPreference);
        return prevPreference;
    }

}
