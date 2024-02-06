package org.players.exceptions;


public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
