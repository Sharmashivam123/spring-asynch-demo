package com.ss.spring_asynch_demo.security.service;

import com.ss.spring_asynch_demo.security.entity.AuthenticationResponse;
import com.ss.spring_asynch_demo.security.entity.UserInfoEntity;
import com.ss.spring_asynch_demo.security.repo.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserInformationRepository repository;
    private final JwtAuthTokenGenerator generator;

    public AuthenticationResponse getJwtAfterAuthentication(Authentication authentication) {
        try {
            UserInfoEntity entity = repository.findByEmailId(authentication.getName()).orElseThrow(() -> {
                log.error("User not found.");
                return new UsernameNotFoundException("User not found.");
            });

            String accessToken = generator.generateAccessToken(authentication);
            log.info("token got generated.");

            return AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .accessTokenExpiry(15 * 60)
                    .username(entity.getUserName())
                    .tokenType(OAuth2AccessToken.TokenType.BEARER)
                    .build();

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Please try again.");
        }
    }
}
