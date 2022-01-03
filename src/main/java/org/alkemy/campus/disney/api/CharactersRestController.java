package org.alkemy.campus.disney.api;

import java.util.List;
import java.util.stream.Collectors;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class CharactersRestController {

  @Autowired
  CharacterRepository characterRepository;

  @GetMapping("/")
  public ResponseEntity<List<?>> getgetCharacters() {
    return ResponseEntity.ok(characterRepository.findAll().stream()
        .map(FictionalCharacter::toShortMap).collect(Collectors.toList()));
  }

}
