package com.saurabh.social_connect.jwts;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    private final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String JWT_PREFIX = "Bearer ";
        final String jwt;
        final String userEmail;

        if(authHeader==null || !authHeader.startsWith(JWT_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(JWT_PREFIX.length());

        userEmail = jwtUtils.extractUsernameFromJWT(jwt);

        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtUtils.isValidToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                log.debug("Authentication Authority: {}", authenticationToken.getAuthorities());
                log.debug("Authentication Credentials: {}", authenticationToken.getCredentials());
            }
        }

        filterChain.doFilter(request, response);

    }

}
