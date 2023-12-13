package com.nts.seonghwan.be.user.dto;

import com.nts.seonghwan.be.user.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SigninResponse extends UserInfoResponse {

    @Setter
    private String sessionId;

    public SigninResponse(Long id, String email) {
        super(id, email);
    }

    public static SigninResponse from(User user) {
        return new SigninResponse(user.getId(), user.getEmail());
    }
}
