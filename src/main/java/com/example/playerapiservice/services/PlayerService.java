package com.example.playerapiservice.services;

import com.example.playerapiservice.entities.Player;
import com.example.playerapiservice.exceptions.InvalidInputException;
import com.example.playerapiservice.exceptions.ResourceNotFoundException;
import com.example.playerapiservice.repositories.PlayerRepository;
import com.example.playerapiservice.validators.PlayerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
    private final PlayerRepository playerRepository;
    private final PlayerValidator playerValidator;

    public Page<Player> getAllPlayers(Pageable pageable) {
        validatePageable(pageable);

        logger.debug("Fetching all players with pageable: {}", pageable);
        List<Player> allPlayers = playerRepository.getAllPlayers();
        if(allPlayers.isEmpty()) {
            logger.debug("No players found");
            return Page.empty();
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allPlayers.size());
        return new PageImpl<>(allPlayers.subList(start, end), pageable, allPlayers.size());
    }

    public Player getPlayerById(String playerID) {
        if(!playerValidator.isValidPlayerID(playerID)) {
            throw new InvalidInputException("Invalid player ID format");
        }
        logger.debug("Fetching player by ID: {}", playerID);
        Optional<Player> playerOptional = playerRepository.getPlayerById(playerID);
        if(playerOptional.isEmpty()) {
            logger.debug("Player with ID {} not found", playerID);
            throw new ResourceNotFoundException("Player with ID " + playerID + " not found");
        }
        return playerOptional.get();
    }

    /*
     We might consider moving this validation logic to a separate validator class if it becomes more complex
     or if we have similar validation logic in other parts of the application
     */
    private void validatePageable(Pageable pageable) {
        if (pageable == null) {
            throw new InvalidInputException("Pageable cannot be null");
        }
        if (pageable.getPageSize() <= 0) {
            throw new InvalidInputException("Page size must be greater than 0");
        }
        if (pageable.getPageNumber() < 0) {
            throw new InvalidInputException("Page number must be non-negative");
        }
    }
}
