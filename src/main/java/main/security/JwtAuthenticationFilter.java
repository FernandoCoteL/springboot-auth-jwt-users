/**
 * Filtro de autenticación JWT para Spring Security.
 * <p>
 * Este filtro intercepta las peticiones HTTP, extrae el token JWT del header Authorization,
 * valida el token y, si es válido, establece la autenticación en el contexto de seguridad.
 * <br>
 * JWT Authentication filter for Spring Security. Intercepts HTTP requests, extracts JWT token
 * from Authorization header, validates it and sets authentication in the security context if valid.
 * <p>
 * Uso típico: Registrar este filtro antes de UsernamePasswordAuthenticationFilter en la cadena de seguridad.
 * Typical usage: Register this filter before UsernamePasswordAuthenticationFilter in the security chain.
 * <p>
 * Autor / Author: Fernando Cote
 * Fecha / Date: 2025-11-24
 */
package main.security;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import main.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.service.UserService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

        /**
         * Intercepta cada petición HTTP y valida el token JWT presente en el header Authorization.
         * Si el token es válido, establece la autenticación en el contexto de seguridad.
         * <br>
         * Intercepts each HTTP request and validates the JWT token present in the Authorization header.
         * If the token is valid, sets authentication in the security context.
         *
         * @param request  petición HTTP / HTTP request
         * @param response respuesta HTTP / HTTP response
         * @param filterChain cadena de filtros / filter chain
         * @throws ServletException si ocurre un error en el filtro / if a filter error occurs
         * @throws IOException si ocurre un error de IO / if an IO error occurs
         */
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
        }

        if (username != null && jwtUtil.validateToken(token, username)) {
            Optional<User> userOpt = userService.findByUserName(username);
            if (userOpt.isPresent()) {
                List<String> roles = userOpt.get().getRole();
                List<GrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                        null,
                        authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}
