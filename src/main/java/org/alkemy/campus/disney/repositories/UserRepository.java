package org.alkemy.campus.disney.repositories;

import org.alkemy.campus.disney.auth.DUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<DUser, Long> {
  DUser findByMail(String mail);
}
