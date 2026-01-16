package com.berkay.ranker.security.service;

import com.berkay.ranker.security.helper.JwtHelper;
import com.berkay.ranker.security.model.CustomUserDetails;
import com.berkay.ranker.user.data.entity.User;
import com.berkay.ranker.user.data.entity.UserToken;
import com.berkay.ranker.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public Authentication login(final HttpServletRequest request) {
        LoginRequest loginRequest;
        try {
            loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
        } catch (IOException e) {
            throw new UsernameNotFoundException("Invalid login request format");
        }

        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new UsernameNotFoundException("Username or password is null");
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (Objects.isNull(userDetails)) {
            throw new UsernameNotFoundException("User not found");
        }

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            final User user = ((CustomUserDetails)userDetails).getUser();

            UserToken userToken = user.getUserToken();

            if (jwtHelper.checkTokenExpired(userToken)) {
                if (Objects.isNull(userToken)) {
                    userToken = new UserToken();
                }

                userToken.setToken(jwtHelper.generateToken(username));
                user.setUserToken(userToken);
            }

            final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,null, null);

            authentication.setDetails(user);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            userService.save(user);

            return authentication;
        } else {
            throw new UsernameNotFoundException("Username or password incorrect");
        }
    }

    public Authentication validate(final HttpServletRequest request) {
        final String token = request.getHeader(jwtHelper.getTokenHeader());
        String username;

        if (StringUtils.isNotEmpty(token)) {
            try {
                username = jwtHelper.extractUsername(token);
            } catch (final Exception e) {
                return null;
            }

            final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (Objects.nonNull(userDetails)) {

                final User user = ((CustomUserDetails) userDetails).getUser();
                final UserToken userToken = user.getUserToken();

                if (Objects.nonNull(userToken) && token.equals(userToken.getToken())) {
                    final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(user);

                    return authentication;
                }
            }
        }
        return null;
    }

    @Data
    private static class LoginRequest {
        private String username;
        private String password;
    }
}