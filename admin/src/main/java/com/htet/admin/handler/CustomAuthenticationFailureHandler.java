package com.htet.admin.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        var message = "Login Fail! Something was wrong!";
        String failureUrl = "/login";

        if (exception instanceof AccountStatusException e){
            if (e instanceof LockedException){
                message = "Please contact to admin for your account! Your Account is locked!";
            }else{
                message = "Please contact to admin for your account! Your Account is inactive!";
            }
        }

        getRedirectStrategy().sendRedirect(request,response,"%s?error=%s".formatted(failureUrl,message));
    }
}
