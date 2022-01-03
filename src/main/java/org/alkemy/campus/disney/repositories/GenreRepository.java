package org.alkemy.campus.disney.repositories;

import java.util.List;
import org.alkemy.campus.disney.model.Genre.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

  @Override
  List<Genre> findAll();
}
