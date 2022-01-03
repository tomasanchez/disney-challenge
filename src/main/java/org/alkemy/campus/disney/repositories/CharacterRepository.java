package org.alkemy.campus.disney.repositories;

import java.util.List;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CharacterRepository extends CrudRepository<FictionalCharacter, Long> {

  List<FictionalCharacter> findByName(String name);

  List<FictionalCharacter> findByAge(int age);

  List<FictionalCharacter> findByNameAndAge(String name, int age);

  @Override
  List<FictionalCharacter> findAll();

}
