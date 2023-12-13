package com.nts.seonghwan.be.user.service;

import com.nts.seonghwan.be.service.ServiceTest;
import com.nts.seonghwan.be.user.dto.*;
import com.nts.seonghwan.be.user.entities.User;
import com.nts.seonghwan.be.user.exception.DuplicatedEmailException;
import com.nts.seonghwan.be.user.exception.ForbiddenUserAccessException;
import com.nts.seonghwan.be.user.exception.InvalidAuthenticationException;
import com.nts.seonghwan.be.user.exception.InvalidRepeatedPasswordException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        User prevUser = getDefaultUser("abc123@abc.com", "1234");
        SignupRequest signupRequest = new SignupRequest("abc123@abc.com", "1234", "1234");

        // when
        // then
        assertThatThrownBy(
                () -> userService.saveUser(signupRequest)
        ).isInstanceOf(DuplicatedEmailException.class);
    }

    @Test
    void saveUser는_비밀번호와_비밀번호_확인이_다르면_InvalidRepeatedPasswordException를_발생시킨다() {
        // given
        SignupRequest signupRequest = new SignupRequest("abc123@abc.com", "1234", "5678");

        // when
        // then
        assertThatThrownBy(
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
        User prevUser = getDefaultUser("abc123@abc.com", "1234");
        String email = "abc123@abc.com";

        // when
        boolean result = userService.validateEmail(email);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    void login은_이메일과_비밀번호가_일치하면_로그인_응답을_반환한다() {
        // given
        User prevUser = getDefaultUser("abc123@abc.com", "1234");
        SigninRequest sigin = new SigninRequest("abc123@abc.com", "1234");

        // when
        SigninResponse signinResponse = userService.login(sigin);

        // then
        assertThat(signinResponse.getEmail()).isEqualTo("abc123@abc.com");
        assertThat(signinResponse.getId()).isEqualTo(prevUser.getId());
    }

    @Test
    void login은_해당하는_이메일의_사용자가_없으면_예외를_던진다(){
        // given
        SigninRequest sigin = new SigninRequest("abc123@abc.com", "1234");

        // when
        // then
        assertThatThrownBy(() -> userService.login(sigin))
                .isInstanceOf(InvalidAuthenticationException.class);
    }

    @Test
    void login은_해당하는_이메일의_사용자가_잘못된_비밀번호로_로그인을_요청하면_예외를_던진다(){
        // given
        User prevUser = getDefaultUser("abc123@abc.com", "1234");
        SigninRequest sigin = new SigninRequest("abc123@abc.com", "1235");

        // when
        // then
        assertThatThrownBy(() -> userService.login(sigin))
                .isInstanceOf(InvalidAuthenticationException.class);
    }

    @Test
    void getUserInfo는_해당하는_사용자의_정보를_반환한다() {
        // given
        User prevUser = getDefaultUser("abc123@abc.com", "1234");

        // when
        UserInfoResponse userInformation = userService.getUserInfo(prevUser.getId());

        // then
        assertThat(userInformation.getEmail()).isEqualTo("abc123@abc.com");
        assertThat(userInformation.getId()).isEqualTo(prevUser.getId());
    }

    @Test
    void getUserInfo는_유요하지_않은_사용자_정보_요청은_예외를_던진다() {
        // given
        User prevUser = getDefaultUser("abc123@abc.com", "1234");

        // when
        // then
        assertThatThrownBy(
                () -> userService.getUserInfo(prevUser.getId() + 1)
        ).isInstanceOf(ForbiddenUserAccessException.class);;
    }

}