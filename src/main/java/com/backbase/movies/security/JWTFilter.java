package com.backbase.movies.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JWTFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        if (StringUtils.hasText(jwt)) {
            jwt = jwt.replaceAll("Bearer ", "");
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey("secret")
                        .parseClaimsJws(jwt)
                        .getBody();
                Authentication authentication =new UsernamePasswordAuthenticationToken(claims.getSubject(),jwt,getAuthorities(claims));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken)) {
            return bearerToken;
        }
        return null;
    }

    List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        if (claims.get("auth") instanceof List){
            List<String> authorities=(List)claims.get("auth");
            return authorities.stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        } else {
            return Stream.of(((String) claims.get("auth")).split(","))
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
    }
}
