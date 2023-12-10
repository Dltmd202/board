package com.nts.seonghwan.be.user.dto;

import com.nts.seonghwan.be.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class SignupRequest {
    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "올바른 이메일 주소를 입력해 주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    private String repeatedPassword;

    public boolean isPasswordEqualToRepeatedPassword() {
        return password.equals(repeatedPassword);
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
