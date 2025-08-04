package com.ss.spring_asynch_demo.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "discovery")
@Getter
@RequiredArgsConstructor
public class DiscoveryApplicationProperties {
    protected final String userServiceUrl;
    protected final String orderServiceUrl;
}
