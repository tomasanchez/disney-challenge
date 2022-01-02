package org.alkemy.campus.disney.repositories;

import org.alkemy.campus.disney.auth.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByMail(String mail);
}
