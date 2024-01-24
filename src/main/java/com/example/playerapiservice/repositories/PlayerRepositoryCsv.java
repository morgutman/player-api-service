package com.example.playerapiservice.repositories;

import com.example.playerapiservice.entities.Player;
import com.example.playerapiservice.utils.CsvUtil;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Setter
public class PlayerRepositoryCsv implements PlayerRepository {
    private static final Logger logger = LoggerFactory.getLogger(PlayerRepositoryCsv.class);
    private static final String CSV_FILE_PATH = "src/main/resources/data/player.csv";
    private Map<String, Player> playersById;

    @PostConstruct
    private void postConstruct() throws FileNotFoundException {
        initializePlayers();
    }

    /*
    I returned an unmodifiable view of the values directly using Collections.unmodifiableCollection(playersById.values()).
    This avoids creating a new list, saving memory and potentially improving performance.
    However, this unmodifiable view prevents modifications to the list.
     */
    @Override
    public List<Player> getAllPlayers() {
        logger.debug("Getting all players");
        return Collections.unmodifiableList(new ArrayList<>(playersById.values()));
    }

    @Override
    public Optional<Player> getPlayerById(String playerID) {
        logger.debug("Getting player by ID: {}", playerID);
        return Optional.ofNullable(playersById.get(playerID));
    }

    private void initializePlayers() throws FileNotFoundException {
        CsvUtil<Player> csvUtil = new CsvUtil<>();
        List<Player> players = csvUtil.readCsv(CSV_FILE_PATH, Player.class);
        // Set playersById to an empty map if the list is empty (let's say this is the appropriate behavior).
        playersById = CollectionUtils.isNotEmpty(players) ? players.stream().collect(Collectors.toMap(Player::getPlayerID, player -> player)) : Collections.emptyMap();
        logger.debug("Initialized {} players", playersById.size());
    }
}
