package org.alkemy.campus.disney.repositories;

import java.util.List;
import org.alkemy.campus.disney.model.Appareance.Appearance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppearanceRepository extends CrudRepository<Appearance, Long> {

  @Override
  List<Appearance> findAll();

  // --------------------------------------------------------------------------------------------
  // Find by Title
  // --------------------------------------------------------------------------------------------

  List<Appearance> findByTitle(String title);

  // List<Appearance> findByOrderbyTitleAsc();

  // List<Appearance> findByOrderbyTitleDesc();

  // --------------------------------------------------------------------------------------------
  // Find by Genre
  // --------------------------------------------------------------------------------------------

  List<Appearance> findByGenre(String genre);

  // List<Appearance> findByGenreOrderByTitleAsc(String genre);

  // List<Appearance> findByGenreOrderByTitleDesc(String genre);

}
