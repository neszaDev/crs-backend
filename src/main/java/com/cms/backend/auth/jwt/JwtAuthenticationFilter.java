package com.cms.backend.auth.jwt;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cms.backend.auth.entity.User;
import com.cms.backend.auth.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final JwtService jwtService;
        private final UserRepository userRepository;

        public JwtAuthenticationFilter(
                        JwtService jwtService,
                        UserRepository userRepository) {

                this.jwtService = jwtService;
                this.userRepository = userRepository;
        }

        @Override
        protected void doFilterInternal(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        FilterChain filterChain)
                        throws ServletException, IOException {

                String authHeader = request.getHeader("Authorization");

                System.out.println(
                                "AUTH HEADER = " + authHeader);

                if (authHeader == null
                                || !authHeader.startsWith("Bearer ")) {

                        filterChain.doFilter(
                                        request,
                                        response);

                        return;
                }

                String jwt = authHeader.substring(7);

                System.out.println(
                                "JWT = " + jwt);

                try {

                        String userId = jwtService.extractUserId(jwt);

                        System.out.println(
                                        "USER ID = " + userId);

                        User user = userRepository.findByPublicIdAndDeletedAtIsNull(
                                        UUID.fromString(userId))
                                        .orElse(null);

                        System.out.println(
                                        "USER FOUND = " + user);

                        if (user != null
                                        && jwtService.isTokenValid(
                                                        jwt,
                                                        user)) {

                                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                                user,
                                                null,
                                                Collections.emptyList());

                                SecurityContextHolder
                                                .getContext()
                                                .setAuthentication(
                                                                authentication);
                        }

                } catch (Exception ex) {

                        SecurityContextHolder.clearContext();
                }

                filterChain.doFilter(
                                request,
                                response);
        }
}