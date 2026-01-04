package com.ss.spring_asynch_demo.security.utils;

import com.ss.spring_asynch_demo.security.entity.UserDetailsInformation;
import com.ss.spring_asynch_demo.security.repo.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtTokenUtility {

    private final UserInformationRepository userInformationRepository;

    public String getUserName(Jwt jwt) {
        return jwt.getSubject();
    }

    public UserDetails getUserDetails(String username) {
        return userInformationRepository.findByEmailId(username)
                .map(UserDetailsInformation::new)
                .orElseThrow(() -> new UsernameNotFoundException("UserEmail: " + username + "not found."));
    }

    public boolean isTokenValid(Jwt jwt, UserDetails userDetails) {
        return ifTokenNotExpired(jwt) && ifTokenUserIsCorrect(getUserName(jwt), userDetails);
    }

    private boolean ifTokenUserIsCorrect(String userName, UserDetails user) {
        return userName.equals(user.getUsername());
    }

    private boolean ifTokenNotExpired(Jwt jwt) {
        return Objects.requireNonNull(jwt.getExpiresAt()).isAfter(Instant.now());
    }
}
