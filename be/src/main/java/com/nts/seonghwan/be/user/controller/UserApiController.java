package com.nts.seonghwan.be.user.controller;

import com.nts.seonghwan.be.common.utils.ApiUtils;
import com.nts.seonghwan.be.common.utils.ApiUtils.ApiResult;
import com.nts.seonghwan.be.user.dto.SigninRequest;
import com.nts.seonghwan.be.user.dto.SigninResponse;
import com.nts.seonghwan.be.user.dto.SignupRequest;
import com.nts.seonghwan.be.user.dto.SignupResponse;
import com.nts.seonghwan.be.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.nts.seonghwan.be.common.constants.SessionConstants.LOGIN_USER;

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

    @PostMapping("/login")
    public ResponseEntity<ApiResult<SigninResponse>> login(
            HttpServletRequest request,
            @Validated @RequestBody SigninRequest signinRequest
    ){
        SigninResponse login = userService.login(signinRequest);
        request.getSession().setAttribute(LOGIN_USER, login.getId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiUtils.success(login));
    }

}
