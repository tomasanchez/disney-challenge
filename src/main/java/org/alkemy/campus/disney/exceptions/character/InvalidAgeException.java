package org.alkemy.campus.disney.exceptions.character;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException() {
        super("A character cannot have an age less than zero (0)");
    }
}
