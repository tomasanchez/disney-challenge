package org.alkemy.campus.disney.services.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.alkemy.campus.disney.model.Appareance.Appearance;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.repositories.AppearanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppearancesProviderService {


  @Autowired
  AppearanceRepository movieRepository;

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

  // --------------------------------------------------------------------------------------------
  // Remove
  // --------------------------------------------------------------------------------------------

  // --------------------------------------------------------------------------------------------
  // Internal Methods
  // --------------------------------------------------------------------------------------------

  private List<?> shorten(List<Appearance> appareances) {
    return appareances.stream().map(Appearance::toShortMap).collect(Collectors.toList());
  }

}
