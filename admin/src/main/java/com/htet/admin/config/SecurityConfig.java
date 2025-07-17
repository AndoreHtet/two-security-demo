package com.htet.admin.config;

import com.htet.admin.handler.CustomAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailService userDetailService;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    public SecurityConfig(CustomUserDetailService userDetailService,CustomAuthenticationFailureHandler authenticationFailureHandler){
        this.userDetailService = userDetailService;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthorizationManager<RequestAuthorizationContext> authorizationManager)
        throws Exception{
        http.authorizeHttpRequests(req -> {
            req.requestMatchers("/login","/css/**","/js/**").permitAll();
            req.anyRequest().access(authorizationManager);
        });

        http.formLogin(form -> {
            form.loginPage("/login");
            form.defaultSuccessUrl("/");
            form.failureHandler(authenticationFailureHandler);
        });

        http.logout(Customizer.withDefaults());

        http.sessionManagement(session -> session.maximumSessions(2));
        return http.build();
    }

    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10,new SecureRandom());
    }

    @Bean
    AuthenticationManager configure(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)throws Exception{
        var builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(getCustomProvider(passwordEncoder));
        return builder.build();
    }

    @Bean
    AuthenticationProvider getCustomProvider(PasswordEncoder encoder){
        var provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(userDetailService);
        return provider;
    }



}
