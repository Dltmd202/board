package com.nts.seonghwan.be.config.web;

import com.nts.seonghwan.be.config.security.SessionInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final SessionInterceptor sessionInterceptor;
    private final CorsConfig corsConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/v1/users/login")
                .excludePathPatterns("/api/v1/users/email")
                .excludePathPatterns("/api/v1/users");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(corsConfig.getAllowedOriginArray())
                .allowedMethods("*")
                .allowedHeaders("*");
    }

}
