package com.nts.seonghwan.be.config.security;

import com.nts.seonghwan.be.user.service.BcryptPasswordEncoder;
import com.nts.seonghwan.be.user.service.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final SimpleSessionManager sessionManager;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BcryptPasswordEncoder();
    }

    @Bean
    public SessionInterceptor sessionManager(){
        return new SessionInterceptor(sessionManager);
    }

    @Bean
    public SessionMangerAttributeArgumentResolver sessionMangerAttributeArgumentResolver() {
    	return new SessionMangerAttributeArgumentResolver(sessionManager);
    }
}
