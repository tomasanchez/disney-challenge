package org.alkemy.campus.disney.services.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.model.Character.FictionalCharacterDTO;
import org.alkemy.campus.disney.repositories.AppearanceRepository;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CharacterProviderService {


  @Autowired
  CharacterRepository characterRepository;

  @Autowired
  AppearanceRepository movieRepository;

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
    FictionalCharacter character = new FictionalCharacter(dto);
    return characterRepository.save(putAppearances(character, dto));
  }

  public FictionalCharacter save(FictionalCharacter character) {
    return characterRepository.save(character);
  }

  public FictionalCharacter update(long id, FictionalCharacterDTO dto) {
    return characterRepository.save(putAppearances(getCharacter(id), dto).update(dto));
  }

  // --------------------------------------------------------------------------------------------
  // Remove
  // --------------------------------------------------------------------------------------------

  public void delete(long id) {
    characterRepository.save(getCharacter(id).detachRelationShips());
    characterRepository.deleteById(id);
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

  public FictionalCharacter putAppearances(FictionalCharacter character,
      FictionalCharacterDTO dto) {

    List<Long> appIds = dto.getAppearances();

    if (Objects.nonNull(appIds)) {

      if (!appIds.isEmpty()) {

        movieRepository.findAllById(appIds).forEach(a -> a.addCharacter(character));
      } else {
        character.getAppearances().clear();
      }
    }

    return character;
  }
}
