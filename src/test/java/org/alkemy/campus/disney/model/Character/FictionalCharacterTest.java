package org.alkemy.campus.disney.model.Character;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.alkemy.campus.disney.exceptions.Character.InvalidAgeException;
import org.alkemy.campus.disney.exceptions.Character.InvalidWeigthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FictionalCharacterTest {

    private FictionalCharacter character;

    @BeforeEach
    void setUp() {
        character = new FictionalCharacter();
    }

    @Test
    void characterCannotHaveLessThan0Years() {
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
}
