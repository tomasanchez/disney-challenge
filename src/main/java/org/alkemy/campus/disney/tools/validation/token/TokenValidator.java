package org.alkemy.campus.disney.tools.validation.token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class TokenValidator {

  private static final String SECRET = "notForProduction";
  private static final String TOKEN_BREARER_PREFIX = "Bearer ";
  private JWTVerifier verifier;

  public TokenValidator() {
    verifier = JWT.require(Algorithm.HMAC256(SECRET.getBytes())).build();
  }

  /**
   * Obtains an User Authentication Token from a header
   * 
   * @param authHeader the authentication header
   * @return the user-password-authentication token object.
   */
  public UsernamePasswordAuthenticationToken retrieveUserAuthTokenFromHeader(String authHeader) {
    return obtainUserAuthToken(decodeToken(obtainTokenFromHeader(authHeader)));
  }


  /**
   * Obtains a tokeken from the authentication header
   * 
   * @param authHeader the authentication header
   * @return the token withouth the prefix 'BEARER '
   */
  private String obtainTokenFromHeader(String authHeader) {
    return authHeader.substring(TOKEN_BREARER_PREFIX.length());
  }

  /**
   * Verifies a token.
   * 
   * @param token to be verified by the validator Algorithm
   * @return a Decoded Jason Web Token
   */
  private DecodedJWT decodeToken(String token) {
    return verifier.verify(token);
  }


  /**
   * Obtains the user auth token from a decoded Jason Web Token.
   * 
   * @param decodedJwt a decoded JSON web Token
   * @return
   */
  private static UsernamePasswordAuthenticationToken obtainUserAuthToken(DecodedJWT decodedJwt) {

    String username = decodedJwt.getSubject();
    String[] roles = decodedJwt.getClaim("roles").asArray(String.class);

    return new UsernamePasswordAuthenticationToken(username, null, listAuthorities(roles));
  }

  /**
   * List all authorities decoded.
   * 
   * @param roles decoded roles.
   * @return a collection of Granted Authorities
   */
  private static Collection<SimpleGrantedAuthority> listAuthorities(String[] roles) {
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    Arrays.stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
    return authorities;
  }
}
