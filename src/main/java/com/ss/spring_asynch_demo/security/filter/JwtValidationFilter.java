package com.ss.spring_asynch_demo.security.filter;

import com.ss.spring_asynch_demo.security.record.RSAKeyRecord;
import com.ss.spring_asynch_demo.security.utils.JwtTokenUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtValidationFilter extends OncePerRequestFilter {

    private final RSAKeyRecord rsaKeyRecord;
    private final JwtDecoder jwtDecoder;
    private final JwtTokenUtility jwtTokenUtility;
    private static final List<String> EXCLUDED_PATHS = List.of("/auth",
            "/h2-console");

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        String path = request.getRequestURI();
        return EXCLUDED_PATHS.stream().anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null
                && authentication.isAuthenticated()
                && authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) authentication;

            Jwt jwt = jwtAuth.getToken();

            String userName = jwtTokenUtility.getUserName(jwt);
            UserDetails userDetails = jwtTokenUtility.getUserDetails(userName);

            if (!userName.equals(userDetails.getUsername())) {
                SecurityContextHolder.clearContext();
                throw new UnavailableException("USer not available.");
            }
        }
        filterChain.doFilter(request, response);
    }
}
