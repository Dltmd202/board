package com.nts.seonghwan.be.user.dto;

import com.nts.seonghwan.be.user.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class SigninResponse {
    private Long id;
    private String email;

    public SigninResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static SigninResponse from(User user) {
        return new SigninResponse(user.getId(), user.getEmail());
    }
}
