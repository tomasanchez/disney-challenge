package org.alkemy.campus.disney.services.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    return getCharacters(null, null, null);
  }

  public List<?> getCharacters(String name, Integer age, Long movies) {

    boolean hasName = !Objects.isNull(name);
    boolean hasAge = !Objects.isNull(age);
    boolean hasMovies = !Objects.isNull(movies);

    if (!hasName && !hasAge && !hasMovies) {
      return shortenCharacters(characterRepository.findAll());
    }

    if (hasName && !hasAge && !hasMovies) {
      return shortenCharacters(characterRepository.findByName(name));
    }

    if (!hasName && hasAge && !hasMovies) {
      return shortenCharacters(characterRepository.findByAge(age));
    }

    if (!hasName && !hasAge && hasMovies) {
      return shortenCharacters(findByAppearance(movies));
    }

    if (hasName && hasAge && !hasMovies) {
      return shortenCharacters(characterRepository.findByNameAndAge(name, age));
    }

    if (hasName && hasAge && hasMovies) {
      return shortenCharacters(
          filterByAppearance(characterRepository.findByNameAndAge(name, age), movies));
    }

    if (hasName && !hasAge && hasMovies) {
      return shortenCharacters(filterByAppearance(characterRepository.findByName(name), movies));
    }

    if (!hasName && hasAge && hasMovies) {
      return shortenCharacters(filterByAppearance(characterRepository.findByAge(age), movies));
    }

    return new ArrayList<>();
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
    characterRepository.deleteById(getCharacter(id).getId());
  }

  // --------------------------------------------------------------------------------------------
  // Internal Methods
  // --------------------------------------------------------------------------------------------

  private List<?> shortenCharacters(List<FictionalCharacter> characters) {
    return characters.stream().map(FictionalCharacter::toShortMap).collect(Collectors.toList());
  }


  private List<FictionalCharacter> findByAppearance(long id) {
    return filterByAppearance(characterRepository.findAll(), id);
  }


  private List<FictionalCharacter> filterByAppearance(List<FictionalCharacter> characters,
      long id) {
    return characters.stream().filter(c -> c.getAppearances().stream().anyMatch(a -> a.matches(id)))
        .collect(Collectors.toList());
  }
}
