package org.alkemy.campus.disney.exceptions.Character;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException() {
        super("A character cannot have an age less than zero (0)");
    }
}
