package com.backbase.movies.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
        public static final String AUTHORIZATION_HEADER = "Authorization";

        @Override
        public void configure(HttpSecurity http) throws Exception {
            JWTFilter customFilter = new JWTFilter();
            http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        }
}
