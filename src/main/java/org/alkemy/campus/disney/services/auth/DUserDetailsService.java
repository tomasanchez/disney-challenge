package org.alkemy.campus.disney.services.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    DUser user = userRepository.findByMail(email);

    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException("No user found with username: " + email);
    }

    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    return new org.springframework.security.core.userdetails.User(user.getMail(),
        user.getPassword().toLowerCase(), enabled, accountNonExpired, credentialsNonExpired,
        accountNonLocked, getAuthorities(user.getRoles()));
  }

  private static List<GrantedAuthority> getAuthorities(List<String> roles) {
    List<GrantedAuthority> authorities = new ArrayList<>();

    for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority(role));
    }

    return authorities;
  }

}
