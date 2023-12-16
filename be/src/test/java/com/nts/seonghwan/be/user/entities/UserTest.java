package com.nts.seonghwan.be.user.entities;

import com.nts.seonghwan.be.user.exception.InvalidAuthenticationException;
import com.nts.seonghwan.be.user.service.BcryptPasswordEncoder;
import com.nts.seonghwan.be.user.service.PasswordEncoder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;


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
        user.signup(passwordEncoder);

        // then
        assertThat(user.getPassword()).isNotEqualTo("1234");
        assertThat(passwordEncoder.matches("1234", user.getPassword())).isEqualTo(true);
    }

    @Test
    void PasswordEncoder로_암호화된_비밀번호를_검증할_수_있다() {
        // given
        User user = User.builder()
                .email("abc123@abc.com")
                .password("1234")
                .build();

        user.signup(passwordEncoder);

        // when
        // then
        assertThatCode(() ->user.login(passwordEncoder, "1234", new InvalidAuthenticationException()))
                .doesNotThrowAnyException();
    }

    @Test
    void PasswordEncoder로_잘못된_비밀번호가_입력되면_인자로_준_예외를_발생시킨다() {
        // given
        User user = User.builder()
                .email("abc123@abc.com")
                .password("1234")
                .build();

        user.signup(passwordEncoder);

        // when
        // then
        assertThatThrownBy(() ->user.login(passwordEncoder, "1235", new InvalidAuthenticationException()))
                .isInstanceOf(InvalidAuthenticationException.class);

    }
}