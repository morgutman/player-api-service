package com.example.playerapiservice.validators;

import org.springframework.stereotype.Component;

@Component
public class PlayerValidator {

    public boolean isValidPlayerID(String playerID) {
        return playerID != null && !playerID.isEmpty();
    }
}
