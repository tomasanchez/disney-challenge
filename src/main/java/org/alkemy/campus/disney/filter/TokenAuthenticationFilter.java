package org.alkemy.campus.disney.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.alkemy.campus.disney.auth.DUser;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private static final int TEN_MINUTES = 10 * 60 * 1_000;

  private final AuthenticationManager authenticationManager;

  public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {

    DUser user = new DUser();

    user.setPassword(request.getParameter("password")).setMail(request.getParameter("mail"));

    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(user.getMail(), user.getPassword());

    return authenticationManager.authenticate(token);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authentication) throws IOException, ServletException {

    User user = (User) authentication.getPrincipal();
    Algorithm algorithm = Algorithm.HMAC256("notForProduction".getBytes());

    // Generating Tokens
    String accesToken = JWT.create().withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + TEN_MINUTES))
        .withIssuer(request.getRequestURL().toString()).withClaim("roles", user.getAuthorities()
            .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
        .sign(algorithm);

    String refreshToken = JWT.create().withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + TEN_MINUTES * 4))
        .withIssuer(request.getRequestURL().toString()).sign(algorithm);


    Map<String, String> tokens = new HashMap<String, String>();
    tokens.put("access_token", accesToken);
    tokens.put("refresh_token", refreshToken);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
  }
}
