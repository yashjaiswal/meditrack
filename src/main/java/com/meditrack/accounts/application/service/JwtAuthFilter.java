package com.meditrack.accounts.application.service;

import com.meditrack.accounts.application.ports.AccountsPersistenceUseCase;
import com.meditrack.accounts.domain.errors.InvalidTokenException;
import com.meditrack.app.ApplicationConstants;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    // OncePerRequestFilter itself extends GenericFilterBean which implements Servlet API's Filter interface
    // We extend its doFilterInternal method to ensure this filter runs only once per request using Spring's built-in Security capabilities

    private final TokensUtil tokensUtil;
    private final AccountsPersistenceUseCase accountsPersistenceUseCase;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer")) {
            String token = authHeader.substring(7); // strip "Bearer "

            try {
                Claims claims = tokensUtil.extractClaims(token);
                String userName = claims.getSubject();
                Long userId = claims.get(ApplicationConstants.USER_ID, Long.class);
                if (!accountsPersistenceUseCase.existsByUserIdAndUserName(userId, userName)) {
                    throw new InvalidTokenException("UserId/UserName does not exist");
                }
                if (userName != null) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userName, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    request.setAttribute(ApplicationConstants.USER_ID, userId);

                } else {
                    throw new InvalidTokenException("UserName missing in the token");


                }

            } catch (Exception e) {
                log.error("Exception occurred at=JwtAuthFilter message={}", e.getMessage());
                throw new InvalidTokenException("Runtime exception while logging in");

            }
        }

        filterChain.doFilter(request, response);
    }
}
