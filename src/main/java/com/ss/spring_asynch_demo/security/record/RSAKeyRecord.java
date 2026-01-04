package com.ss.spring_asynch_demo.security.record;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.security.jwt")
public class RSAKeyRecord {
    private final RSAPrivateKey rsaPrivateKey;
    private final RSAPublicKey rsaPublicKey;
}
