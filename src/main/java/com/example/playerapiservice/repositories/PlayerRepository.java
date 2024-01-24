package com.example.playerapiservice.repositories;

import com.example.playerapiservice.entities.Player;

import java.util.List;
import java.util.Optional;

/*
    The PlayerRepository interface is designed to facilitate future integration with a database
    by replacing PlayerRepositoryCsv impl with PlayerRepositoryDb impl.
 */
public interface PlayerRepository {
    List<Player> getAllPlayers();
    Optional<Player> getPlayerById(String playerID);
}
