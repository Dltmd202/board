package com.nts.seonghwan.be.config;

import com.nts.seonghwan.be.user.service.BcryptPasswordEncoder;
import com.nts.seonghwan.be.user.service.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BcryptPasswordEncoder();
    }
}
