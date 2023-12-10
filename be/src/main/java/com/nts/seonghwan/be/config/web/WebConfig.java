package com.nts.seonghwan.be.config.web;

import com.nts.seonghwan.be.config.security.SessionInterceptor;
import com.nts.seonghwan.be.config.security.SessionMangerAttributeArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final SessionMangerAttributeArgumentResolver sessionMangerAttributeArgumentResolver;
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

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(sessionMangerAttributeArgumentResolver);
    }
}
