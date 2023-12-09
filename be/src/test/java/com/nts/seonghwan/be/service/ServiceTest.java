package com.nts.seonghwan.be.service;

import com.nts.seonghwan.be.user.service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class ServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public EntityManager em;

}
