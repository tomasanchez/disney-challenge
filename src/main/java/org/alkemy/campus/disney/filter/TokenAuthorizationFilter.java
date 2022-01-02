package org.alkemy.campus.disney.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthorizationFilter extends OncePerRequestFilter {

  private static final String TOKEN_PREFIX = "Bearer ";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    if (request.getServletPath().contains("/login")) {
      filterChain.doFilter(request, response);
    } else {
      String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

      if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
        try {
          UsernamePasswordAuthenticationToken authToken = buildAuthenticationToken(authHeader);
          SecurityContextHolder.getContext().setAuthentication(authToken);
          filterChain.doFilter(request, response);
        } catch (Exception e) {

          response.setHeader("error", e.getMessage());
          response.setStatus(HttpStatus.UNAUTHORIZED.value());
          Map<String, String> errors = new HashMap<>();
          errors.put("error_message", e.getMessage());
          new ObjectMapper().writeValue(response.getOutputStream(), errors);
        }
      } else {
        filterChain.doFilter(request, response);
      }
    }
  }

  private UsernamePasswordAuthenticationToken buildAuthenticationToken(String authHeader) {
    String token = authHeader.substring(TOKEN_PREFIX.length());
    Algorithm algorithm = Algorithm.HMAC256("notForProduction".getBytes());
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT decodedJwt = verifier.verify(token);
    String username = decodedJwt.getSubject();
    String[] roles = decodedJwt.getClaim("roles").asArray(String.class);

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    Arrays.stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

    UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(username, null, authorities);
    return authToken;
  }
}
