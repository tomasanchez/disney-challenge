package org.alkemy.campus.disney.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.tools.validation.token.TokenValidator;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    String issuer = request.getRequestURL().toString();
    TokenValidator tokenValidator = new TokenValidator();

    // Generating Tokens
    String accesToken = tokenValidator.generateTokenForUser(user, TEN_MINUTES * 3, issuer, true);
    String refreshToken = tokenValidator.generateTokenForUser(user, TEN_MINUTES * 6, issuer, false);


    Map<String, String> tokens = new HashMap<String, String>();
    tokens.put("access_token", accesToken);
    tokens.put("refresh_token", refreshToken);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
  }
}
