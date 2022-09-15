package com.example.vaccinationcard.configurations;

import com.example.vaccinationcard.models.User;
import com.example.vaccinationcard.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
@AllArgsConstructor
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        boolean valido = tokenService.isValid(token);
        if (valido) {
            authClient(token);
        }
        filterChain.doFilter(request,response);
    }

    private void authClient(String token){
        Long id = tokenService.getId(token);
        Optional<User> user = userService.findUserById(id);
        if(user.isPresent()){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER);
        if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return null;
        }
        return token.substring(7);
    }
}
