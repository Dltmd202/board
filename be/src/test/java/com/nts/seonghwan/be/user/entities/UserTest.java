package com.nts.seonghwan.be.user.entities;

import com.nts.seonghwan.be.user.exception.InvalidAuthenticationException;
import com.nts.seonghwan.be.user.service.BcryptPasswordEncoder;
import com.nts.seonghwan.be.user.service.PasswordEncoder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserTest {
    PasswordEncoder passwordEncoder = new BcryptPasswordEncoder();

    @Test
    void PassswordEncoder로_비밀번호_암호화할_수_있다() {
        // given
        User user = User.builder()
                .email("abc123@abc.com")
                .password("1234")
                .build();

        // when
        user.encryptPassword(passwordEncoder);

        // then
        assertThat(user.getPassword()).isNotEqualTo("1234");
        assertThat(passwordEncoder.matches("1234", user.getPassword())).isEqualTo(true);
    }
}