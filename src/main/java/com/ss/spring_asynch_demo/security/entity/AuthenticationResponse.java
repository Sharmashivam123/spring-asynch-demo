package com.ss.spring_asynch_demo.security.entity;

import lombok.Builder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@Builder
public record AuthenticationResponse(String accessToken,
                                     int accessTokenExpiry,
                                     OAuth2AccessToken.TokenType tokenType,
                                     String username) {
}
