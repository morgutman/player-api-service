package com.example.playerapiservice.api.controlles;

import com.example.playerapiservice.entities.Player;
import com.example.playerapiservice.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
Any exceptions thrown during the execution of playerService.getAllPlayers or playerService.getPlayerById
will be caught by the GlobalExceptionHandler, which will then generate an appropriate ResponseEntity<ErrorResponse>.
 */
@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {
    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    private final PlayerService playerService;

    // By implementing pagination here, we're optimizing the response size and enhancing performance.
    // We could also you another pagination approach that suits larger data sets better
    @GetMapping
    public ResponseEntity<Page<Player>> getAllPlayers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        logger.info("Fetching all players. Page: {}, Size: {}", page, size);
        Page<Player> players = playerService.getAllPlayers(PageRequest.of(page, size));
        return ResponseEntity.ok(players);
    }

    /*
    To optimize response size, i would create a smaller Player object here with only the necessary fields (PlayerResponse / PlayerDTO)
    */
    @GetMapping("/{playerID}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String playerID) {
        logger.info("Fetching player by ID: {}", playerID);
        return ResponseEntity.ok(playerService.getPlayerById(playerID));
    }
}
