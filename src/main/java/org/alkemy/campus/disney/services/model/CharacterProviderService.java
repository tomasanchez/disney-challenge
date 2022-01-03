package org.alkemy.campus.disney.services.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.model.Character.FictionalCharacterDTO;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CharacterProviderService {


  @Autowired
  CharacterRepository characterRepository;

  // --------------------------------------------------------------------------------------------
  // Getters
  // --------------------------------------------------------------------------------------------

  public FictionalCharacter getCharacter(long id) {
    Optional<FictionalCharacter> character = characterRepository.findById(id);

    if (!character.isPresent()) {
      throw new EntityNotFoundException("No Fictional character found with id: " + id);
    }

    return character.get();
  }

  public List<?> getCharacters() {
    return characterRepository.findAll().stream().map(FictionalCharacter::toShortMap)
        .collect(Collectors.toList());
  }

  // --------------------------------------------------------------------------------------------
  // Save
  // --------------------------------------------------------------------------------------------

  public FictionalCharacter save(FictionalCharacterDTO dto) {
    return characterRepository.save(new FictionalCharacter(dto));
  }

  public FictionalCharacter save(FictionalCharacter character) {
    return characterRepository.save(character);
  }

  public FictionalCharacter update(long id, FictionalCharacterDTO dto) {
    return characterRepository.save(getCharacter(id).update(dto));
  }

  // --------------------------------------------------------------------------------------------
  // Remove
  // --------------------------------------------------------------------------------------------

  public void delete(long id) {
    characterRepository.deleteById(id);
  }

}
