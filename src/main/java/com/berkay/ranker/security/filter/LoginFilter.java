package com.berkay.ranker.security.filter;

import com.berkay.ranker.common.response.ErrorResponse;
import com.berkay.ranker.security.service.AuthServiceImpl;
import com.berkay.ranker.user.data.entity.User;
import com.berkay.ranker.user.data.entity.UserToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.time.LocalDateTime;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    private final AuthServiceImpl authService;

    public LoginFilter(String loginPath, AuthServiceImpl authService){
        super(loginPath);
        this.authService = authService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException{
        return authService.login(request);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException{
        final User user = (User) authResult.getDetails();
        final UserToken userToken = user.getUserToken();
        response.setHeader("X-Header-Token", userToken.getToken());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        new ObjectMapper().writeValue(response.getWriter(), "Login Successful");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException{
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setMessage(failed.getMessage());
        errorResponse.setErrorCode("AUTH_FAILED");
        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }
}