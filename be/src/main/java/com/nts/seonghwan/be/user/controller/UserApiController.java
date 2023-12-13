package com.nts.seonghwan.be.user.controller;

import com.nts.seonghwan.be.common.utils.ApiUtils;
import com.nts.seonghwan.be.common.utils.ApiUtils.ApiResult;
import com.nts.seonghwan.be.config.security.SessionManagerAttribute;
import com.nts.seonghwan.be.config.security.SimpleSessionManager;
import com.nts.seonghwan.be.user.dto.*;
import com.nts.seonghwan.be.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserApiController {
    private final SimpleSessionManager simpleSessionManager;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResult<SignupResponse>> singUp(
            @Validated @RequestBody SignupRequest signupRequest
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiUtils.success(userService.saveUser(signupRequest)));
    }

    @GetMapping("/email")
    public ResponseEntity<ApiResult<Boolean>> validateEmail(
            @RequestParam("email") String email
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiUtils.success(userService.validateEmail(email)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResult<SigninResponse>> login(
            @Validated @RequestBody SigninRequest signinRequest
    ){
        SigninResponse login = userService.login(signinRequest);
        String sessionId = simpleSessionManager.createSession(login.getId());
        login.setSessionId(sessionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiUtils.success(login));
    }

    @GetMapping("/info")
    public ResponseEntity<ApiResult<UserInfoResponse>> getUserInfo(
            @SessionManagerAttribute Long userId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiUtils.success(userService.getUserInfo(userId)));
    }
}
