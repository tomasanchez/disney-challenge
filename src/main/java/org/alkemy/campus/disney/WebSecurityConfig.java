package org.alkemy.campus.disney;

import javax.servlet.http.HttpServletResponse;
import org.alkemy.campus.disney.filter.TokenAuthenticationFilter;
import org.alkemy.campus.disney.services.auth.DUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DUserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Set Filter URL
    TokenAuthenticationFilter tokenFilter =
        new TokenAuthenticationFilter(authenticationManagerBean());
    tokenFilter.setFilterProcessesUrl("/auth/login");

    // Enable CORS and disable CSRF
    http.cors().and().csrf().disable();

    // Set session management to stateless
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Set unauthorized requests exception handler
    http = http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    }).and();

    // Allow all into /auth/
    http.authorizeRequests().antMatchers("/auth/**").permitAll();
    // Restrict all other routes
    http.authorizeHttpRequests().anyRequest().authenticated();
    // Add JWT token filter
    http.addFilter(tokenFilter);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider());
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(encoder());
    return authProvider;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
