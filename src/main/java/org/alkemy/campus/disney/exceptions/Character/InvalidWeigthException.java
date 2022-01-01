package org.alkemy.campus.disney.exceptions.Character;

public class InvalidWeigthException extends RuntimeException {
    public InvalidWeigthException() {
        super("Character cannot have weight less than zero (0) ");
    }
}
