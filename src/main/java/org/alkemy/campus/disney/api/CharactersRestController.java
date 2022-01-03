package org.alkemy.campus.disney.api;

import java.net.URI;
import java.util.List;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.model.Character.FictionalCharacterDTO;
import org.alkemy.campus.disney.services.model.CharacterProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/characters")
public class CharactersRestController extends BaseRestController {

  // --------------------------------------------------------------------------------------------
  // Autowireds
  // --------------------------------------------------------------------------------------------

  @Autowired
  CharacterProviderService characterService;

  // --------------------------------------------------------------------------------------------
  // Get
  // --------------------------------------------------------------------------------------------

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<?>> getCharacters(@RequestParam(required = false) String name,
      @RequestParam(required = false) Integer age, @RequestParam(required = false) Long movies) {
    return ResponseEntity.ok(characterService.getCharacters(name, age, movies));
  }


  @GetMapping("/{id}")
  public ResponseEntity<FictionalCharacter> getCharacters(@PathVariable long id) {
    return ResponseEntity.ok(characterService.getCharacter(id));
  }

  // --------------------------------------------------------------------------------------------
  // Post
  // --------------------------------------------------------------------------------------------

  @PostMapping(produces = "application/json", consumes = {"application/json"})
  public ResponseEntity<FictionalCharacter> postCharacter(
      @Validated @RequestBody FictionalCharacterDTO characterDTO) {

    URI uri = URI.create(
        ServletUriComponentsBuilder.fromCurrentContextPath().path("/characters").toUriString());

    return ResponseEntity.created(uri).body(characterService.save(characterDTO));
  }

  // --------------------------------------------------------------------------------------------
  // Patch/Put
  // --------------------------------------------------------------------------------------------

  @PutMapping(path = "/{id}", produces = "application/json", consumes = {"application/json"})
  public ResponseEntity<FictionalCharacter> updateCharacter(@PathVariable long id,
      @Validated @RequestBody FictionalCharacterDTO characterDTO) {
    return ResponseEntity.ok(characterService.update(id, characterDTO));
  }

  // --------------------------------------------------------------------------------------------
  // Delete
  // --------------------------------------------------------------------------------------------

  @DeleteMapping(path = "/{id}", produces = "application/json")
  public ResponseEntity<?> deleteCharacter(@PathVariable long id) {
    characterService.delete(id);
    return ResponseEntity.ok(null);
  }

  // --------------------------------------------------------------------------------------------
  // Internal Methods
  // --------------------------------------------------------------------------------------------

}
