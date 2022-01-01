package org.alkemy.campus.disney.repositories;

import java.util.List;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.springframework.data.repository.CrudRepository;


public interface CharacterRepository extends CrudRepository<FictionalCharacter, Long> {

    List<FictionalCharacter> findByName(String name);

}
