package com.nts.seonghwan.be.user.dto;

import com.nts.seonghwan.be.user.entities.User;
import lombok.Getter;

@Getter
public class UserInfoResponse {
    private Long id;
    private String email;

    public UserInfoResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static UserInfoResponse from(User user) {
        return new UserInfoResponse(user.getId(), user.getEmail());
    }
}
