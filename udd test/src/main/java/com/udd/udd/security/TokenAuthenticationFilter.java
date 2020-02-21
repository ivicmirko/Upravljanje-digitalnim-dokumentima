package com.udd.udd.security;

import com.udd.udd.jwt.JwtTokenProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {


    private JwtTokenProvider jwtTokenProvider;

    private UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(JwtTokenProvider jwtTokenProvider,UserDetailsService userDetailsService){
        this.jwtTokenProvider=jwtTokenProvider;
        this.userDetailsService=userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String username;
        String authToken = jwtTokenProvider.getToken(httpServletRequest);

        if (authToken != null) {
            // uzmi username iz tokena
            username = jwtTokenProvider.getUsernameFromToken(authToken);

            if (username != null) {
                // uzmi user-a na osnovu username-a
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // proveri da li je prosledjeni token validan
                if (jwtTokenProvider.validateToken(authToken, userDetails)) {
                    // kreiraj autentifikaciju
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
