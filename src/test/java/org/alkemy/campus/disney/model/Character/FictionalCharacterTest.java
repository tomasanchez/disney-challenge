package org.alkemy.campus.disney.model.Character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.alkemy.campus.disney.exceptions.character.InvalidAgeException;
import org.alkemy.campus.disney.exceptions.character.InvalidWeigthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FictionalCharacterTest {

  private FictionalCharacter character;

  @BeforeEach
  void setUp() {
    character = new FictionalCharacter();
  }

  @Test
  void characterCannotBeLessThan0Years() {
    assertThrows(InvalidAgeException.class, () -> {
      character.setAge(-1);
    });
  }

  @Test
  void characterCannotWeigthLessThan0() {
    assertThrows(InvalidWeigthException.class, () -> {
      character.setWeight(-1);
    });
  }

  @Test
  void characterDoesParseToJson() {
    assertEquals(
        "{\"id\":null,\"image\":\"\",\"name\":null,\"age\":0,\"weight\":0.0,\"story\":null,\"appearances\":[]}",
        character.toString());
  }
}
