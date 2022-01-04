package org.alkemy.campus.disney.services.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.alkemy.campus.disney.model.Appareance.Appearance;
import org.alkemy.campus.disney.model.Appareance.AppearanceDTO;
import org.alkemy.campus.disney.model.Appareance.Movie;
import org.alkemy.campus.disney.model.Appareance.Series;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.repositories.AppearanceRepository;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.alkemy.campus.disney.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppearancesProviderService {


  @Autowired
  AppearanceRepository movieRepository;

  @Autowired
  CharacterRepository characterRepository;

  @Autowired
  GenreRepository genreRepository;

  // --------------------------------------------------------------------------------------------
  // Getters
  // --------------------------------------------------------------------------------------------
  public Appearance getMovie(long id) {
    Optional<Appearance> movie = movieRepository.findById(id);

    if (!movie.isPresent()) {
      throw new EntityNotFoundException("No Appearance found with id: " + id);
    }

    return movie.get();
  }

  public Map<String, ?> getMovieDTO(long id) {
    Appearance appearance = getMovie(id);

    Map<String, Object> jsonMap = new HashMap<>();

    jsonMap.put("id", appearance.getId());
    jsonMap.put("image", appearance.getImage());
    jsonMap.put("title", appearance.getTitle());
    jsonMap.put("releaseDate", appearance.getReleaseDate());
    jsonMap.put("rating", appearance.getRating());
    jsonMap.put("genre", appearance.getGenre());
    jsonMap.put("characters", appearance.getCharacters().stream()
        .map(FictionalCharacter::toShortMap).collect(Collectors.toList()));

    return jsonMap;
  }

  public List<?> getMovies() {
    return shorten(movieRepository.findAll());
  }

  // --------------------------------------------------------------------------------------------
  // Save
  // --------------------------------------------------------------------------------------------

  public Appearance save(AppearanceDTO dto) {
    Appearance appearance = dto.getType() == 2 ? new Series(dto) : new Movie(dto);

    putCharacters(dto, appearance);

    putGenre(dto, appearance);

    return movieRepository.save(appearance);
  }

  public Appearance save(long id, AppearanceDTO dto) {
    Appearance appearance;

    try {
      appearance = getMovie(id);
    } catch (EntityNotFoundException e) {
      return save(dto);
    }

    // Updates Genre
    putGenre(dto, appearance);

    // Updates Characters
    putCharacters(dto, appearance);

    return movieRepository.save(appearance.update(dto));
  }

  // --------------------------------------------------------------------------------------------
  // Remove
  // --------------------------------------------------------------------------------------------

  public void delete(long id) {
    movieRepository.save(getMovie(id).detachRelationShips());
    movieRepository.deleteById(id);
  }
  // --------------------------------------------------------------------------------------------
  // Internal Methods
  // --------------------------------------------------------------------------------------------

  private List<?> shorten(List<Appearance> appareances) {
    return appareances.stream().map(Appearance::toShortMap).collect(Collectors.toList());
  }

  private void putGenre(AppearanceDTO dto, Appearance appearance) {

    Long genreId = dto.getGenre();

    // If null do nothing
    if (Objects.nonNull(genreId)) {
      // When Genre ID is valid, overwrite
      if (genreId > 0) {
        genreRepository.findById(genreId).ifPresentOrElse(g -> appearance.setGenre(g),
            EntityNotFoundException::new);
      }
      // else detach relationship
      else {
        appearance.setGenre(null);
      }
    }
  }

  private void putCharacters(AppearanceDTO dto, Appearance appearance) {
    List<Long> characterIds = dto.getCharacters();
    // If null do nothing
    if (Objects.nonNull(characterIds)) {
      // When not empty
      if (!characterIds.isEmpty()) {
        characterRepository.findAllById(characterIds).forEach(c -> appearance.addCharacter(c));
      }
      // When empty delete all entries
      else {
        appearance.getCharacters().clear();
      }
    }
  }
}
