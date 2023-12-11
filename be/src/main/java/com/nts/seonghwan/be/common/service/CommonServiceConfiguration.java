package com.nts.seonghwan.be.common.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonServiceConfiguration {

    @Bean
    public UUIDHolder uuidHolder() {
        return new UUIDHolderImpl();
    }
}
