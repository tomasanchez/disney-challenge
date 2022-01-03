package org.alkemy.campus.disney.api;

import java.net.URI;
import java.util.List;
import java.util.Map;
import org.alkemy.campus.disney.model.Appareance.Appearance;
import org.alkemy.campus.disney.model.Appareance.AppearanceDTO;
import org.alkemy.campus.disney.services.model.AppearancesProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/movies")
public class MovieRestController extends BaseRestController {

  // --------------------------------------------------------------------------------------------
  // Autowireds
  // --------------------------------------------------------------------------------------------

  @Autowired
  private AppearancesProviderService movieService;

  // --------------------------------------------------------------------------------------------
  // Get
  // --------------------------------------------------------------------------------------------

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<?>> getMovies() {
    return ResponseEntity.ok(movieService.getMovies());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, ?>> getMovies(@PathVariable long id) {
    return ResponseEntity.ok(movieService.getMovieDTO(id));
  }

  // --------------------------------------------------------------------------------------------
  // Post
  // --------------------------------------------------------------------------------------------
  @PostMapping(produces = "application/json")
  public ResponseEntity<Appearance> postMovies(@Validated @RequestBody AppearanceDTO dto) {

    URI uri = URI
        .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/movies").toUriString());

    return ResponseEntity.created(uri).body(movieService.save(dto));
  }
}


