package com.nts.seonghwan.be.user.entities;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.user.service.PasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public void encryptPassword(PasswordEncoder encoder){
        this.password = encoder.encode(password);
    }

    public void login(PasswordEncoder passwordEncoder, String password, BusinessException exception) {
        if(!passwordEncoder.matches(password, this.password))
            throw exception;
    }
}
