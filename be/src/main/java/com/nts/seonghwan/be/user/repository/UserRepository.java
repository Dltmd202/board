package com.nts.seonghwan.be.user.repository;

import com.nts.seonghwan.be.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
