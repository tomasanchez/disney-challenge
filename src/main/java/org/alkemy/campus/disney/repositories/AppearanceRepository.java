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

  List<Appearance> findByTitleOrderByReleaseDateAsc(String title);

  List<Appearance> findByTitleOrderByReleaseDateDesc(String title);

  // --------------------------------------------------------------------------------------------
  // Order By Title
  // --------------------------------------------------------------------------------------------

  List<Appearance> findByOrderByTitleAsc();

  List<Appearance> findByOrderByTitleDesc();


}
