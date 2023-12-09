package com.nts.seonghwan.be.config.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "cors")
public class CorsConfig {
    private List<String> allowedOrigins;

    public String[] getAllowedOriginArray(){
        return allowedOrigins.toArray(String[]::new);
    }
}
