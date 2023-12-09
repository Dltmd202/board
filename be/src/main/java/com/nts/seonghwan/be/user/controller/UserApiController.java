package com.nts.seonghwan.be.user.controller;

import com.nts.seonghwan.be.common.utils.ApiUtils;
import com.nts.seonghwan.be.common.utils.ApiUtils.ApiResult;
import com.nts.seonghwan.be.user.dto.SignupRequest;
import com.nts.seonghwan.be.user.dto.SignupResponse;
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
}
