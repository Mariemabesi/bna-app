package com.example.bna.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);  // Add the logger

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // Debug log for Authorization header
        logger.debug("Authorization header: {}", header);

        // Check if the Authorization header is present and starts with "Bearer "
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);  // Extract the token from the header
            logger.debug("Extracted token: {}", token);  // Log the extracted token

            if (jwtUtils.validateToken(token)) { // Validate the token
                username = jwtUtils.getUsernameFromToken(token); // Extract the username from the token
                logger.debug("Username extracted from token: {}", username);  // Log the username extracted from the token
            }
        }

        // If the username is found and the authentication is not set yet
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load the user details from the username
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Create the authentication token for Spring Security
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

            // Set the details for the request (like IP address)
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Set the authentication object in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Proceed with the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
