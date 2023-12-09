package com.nts.seonghwan.be.user.service;

import com.nts.seonghwan.be.service.ServiceTest;
import com.nts.seonghwan.be.user.dto.SignupRequest;
import com.nts.seonghwan.be.user.dto.SignupResponse;
import com.nts.seonghwan.be.user.entities.User;
import com.nts.seonghwan.be.user.exception.DuplicatedEmailException;
import com.nts.seonghwan.be.user.exception.InvalidRepeatedPasswordException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class UserServiceTest extends ServiceTest {

    @Test
    void saveUser는_사용하지_않았던_이메일에_대해_유저를_저장한다() {
        // given
        SignupRequest signupRequest = new SignupRequest("abc123@abc.com", "1234", "1234");

        // when
        SignupResponse signupResponse = userService.saveUser(signupRequest);

        // then
        assertThat(signupResponse.getEmail()).isEqualTo("abc123@abc.com");
    }

    @Test
    void saveUser는_사용했던_이메일로_요청하면_InvalidRepeatedPasswordException를_발생시킨다() {
        // given
        User prevUser = User.builder()
                .email("abc123@abc.com")
                .password("1234")
                .build();
        em.persist(prevUser);
        SignupRequest signupRequest = new SignupRequest("abc123@abc.com", "1234", "1234");

        // when
        // then
        Assertions.assertThatThrownBy(
                () -> userService.saveUser(signupRequest)
        ).isInstanceOf(DuplicatedEmailException.class);
    }

    @Test
    void saveUser는_비밀번호와_비밀번호_확인이_다르면_InvalidRepeatedPasswordException를_발생시킨다() {
        // given
        SignupRequest signupRequest = new SignupRequest("abc123@abc.com", "1234", "5678");

        // when
        // then
        Assertions.assertThatThrownBy(
                () -> userService.saveUser(signupRequest)
        ).isInstanceOf(InvalidRepeatedPasswordException.class);
    }

    @Test
    void validateEmail은_사용하지_않았던_이메일에_대해_true를_반환한다() {
        // given
        String email = "abc123@abc.com";

        // when
        boolean result = userService.validateEmail(email);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    void validateEmail은_사용했던_이메일에_대해_false를_반환한다() {
        // given
        User prevUser = User.builder()
                .email("abc123@abc.com")
                .password("1234")
                .build();
        em.persist(prevUser);

        String email = "abc123@abc.com";

        // when
        boolean result = userService.validateEmail(email);

        // then
        assertThat(result).isEqualTo(false);
    }

}