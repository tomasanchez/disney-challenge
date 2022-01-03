package org.alkemy.campus.disney.api;

import java.util.List;
import java.util.Map;
import org.alkemy.campus.disney.services.model.AppearancesProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
