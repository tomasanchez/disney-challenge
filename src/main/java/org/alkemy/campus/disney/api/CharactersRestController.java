package org.alkemy.campus.disney.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.model.Character.FictionalCharacterDTO;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/characters")
public class CharactersRestController extends BaseRestController {

  @Autowired
  CharacterRepository characterRepository;

  @GetMapping()
  public ResponseEntity<List<?>> getCharacters() {
    return ResponseEntity.ok(characterRepository.findAll().stream()
        .map(FictionalCharacter::toShortMap).collect(Collectors.toList()));
  }

  @PostMapping(produces = "application/json", consumes = {"application/json"})
  public ResponseEntity<FictionalCharacter> postCharacter(
      @Validated @RequestBody FictionalCharacterDTO characterDTO) {

    URI uri = URI.create(
        ServletUriComponentsBuilder.fromCurrentContextPath().path("/characters").toUriString());

    return ResponseEntity.created(uri)
        .body(characterRepository.save(new FictionalCharacter(characterDTO)));
  }
}
