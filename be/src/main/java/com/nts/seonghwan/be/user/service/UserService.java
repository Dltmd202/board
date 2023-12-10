package com.nts.seonghwan.be.user.service;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.user.dto.SigninRequest;
import com.nts.seonghwan.be.user.dto.SigninResponse;
import com.nts.seonghwan.be.user.dto.SignupRequest;
import com.nts.seonghwan.be.user.dto.SignupResponse;
import com.nts.seonghwan.be.user.entities.User;
import com.nts.seonghwan.be.user.exception.DuplicatedEmailException;
import com.nts.seonghwan.be.user.exception.InvalidAuthenticationException;
import com.nts.seonghwan.be.user.exception.InvalidRepeatedPasswordException;
import com.nts.seonghwan.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional()
    public SignupResponse saveUser(SignupRequest signupRequest) {
        validateSignupRequestForm(signupRequest);
        validateDuplicatedEmail(signupRequest.getEmail());

        User user = signupRequest.toEntity();
        user.encryptPassword(passwordEncoder);
        User save = userRepository.save(user);
        return SignupResponse.toResponse(save);
    }

    @Transactional(readOnly = true)
    public boolean validateEmail(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public SigninResponse login(SigninRequest signinRequest) {
        User user = getUserByEmail(signinRequest.getEmail(), new InvalidAuthenticationException());
        user.login(passwordEncoder, signinRequest.getPassword(), new InvalidAuthenticationException());

        return SigninResponse.from(user);
    }

    private void validateSignupRequestForm(SignupRequest signupRequest) {
        if(!signupRequest.isPasswordEqualToRepeatedPassword())
            throw new InvalidRepeatedPasswordException();
    }

    private void validateDuplicatedEmail(String email) {
        if(userRepository.existsByEmail(email))
            throw new DuplicatedEmailException();
    }

    private User getUserByEmail(String email, BusinessException exception) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> exception);
    }
}
