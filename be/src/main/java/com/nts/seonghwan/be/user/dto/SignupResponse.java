package com.nts.seonghwan.be.user.dto;

import com.nts.seonghwan.be.user.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class SignupResponse {
    private Long id;
    private String email;

    public SignupResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static SignupResponse toResponse(User user) {
        return new SignupResponse(user.getId(), user.getEmail());
    }
}
