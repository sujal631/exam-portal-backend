package com.exam.exam_portal.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.exam_portal.service.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String tokenHeader = request.getHeader("Authorization");
        String userName = null;
        String jwtToken = null;

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            try {
                // Get the JWT Token from header
                jwtToken = tokenHeader.substring(7);
                userName = this.jwtUtils.extractUsername(jwtToken);
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
                System.out.println("Token expired.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Token error.");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer string.");
        }

        // validate token
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(userName);
            if (this.jwtUtils.validateToken(jwtToken, userDetails)) {
                // token is valid
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } else {
            System.out.println("Invalid token.");
        }

        filterChain.doFilter(request, response);
    }

}
