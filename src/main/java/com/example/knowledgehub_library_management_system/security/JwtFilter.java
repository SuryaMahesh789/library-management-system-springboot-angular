package com.example.knowledgehub_library_management_system.security;

import com.example.knowledgehub_library_management_system.common.entity.User;
import com.example.knowledgehub_library_management_system.user.repository.UserRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {

        String authHeader = request.getHeader("Authorization");

        if(authHeader!=null && authHeader.startsWith("Bearer "))
        {
            String token = authHeader.substring(7);

            if(jwtUtil.isTokenValid(token))
            {
                String email = jwtUtil.extractEmail(token);
                User user = userRepository.findByEmail(email).orElse(null);

                if(user!=null)
                {
//                    before rbac
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,null, List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole().getName())));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("Authenticated user: "+email);

                    System.out.println("ROLE: " + user.getRole().getName());

                }

            }
        }


        filterChain.doFilter(request,response);
    }

}
