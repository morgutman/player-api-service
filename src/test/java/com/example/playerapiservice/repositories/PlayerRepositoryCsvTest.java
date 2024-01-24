package com.example.playerapiservice.repositories;

import com.example.playerapiservice.AbstractPlayerApiServiceTest;
import com.example.playerapiservice.entities.Player;
import com.example.playerapiservice.utils.CsvUtil;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class PlayerRepositoryCsvTest extends AbstractPlayerApiServiceTest {
    @Mock
    private CsvUtil<Player> csvUtil;
    @InjectMocks
    private PlayerRepositoryCsv playerRepositoryCsv;
    private List<Player> players;

    @Before
    public void setUp() throws FileNotFoundException {
        MockitoAnnotations.openMocks(this);
        players = setupPlayers();
        when(csvUtil.readCsv(anyString(), eq(Player.class))).thenReturn(players);
        playerRepositoryCsv.setPlayersById(players.stream().collect(Collectors.toMap(Player::getPlayerID, player -> player)));
    }

    @Test
    public void testGetAllPlayers_Success() {
        // Act
        List<Player> actualPlayers = playerRepositoryCsv.getAllPlayers();

        // Assert
        assertTrue(CollectionUtils.isNotEmpty(actualPlayers));
        assertEquals(players, actualPlayers);
    }

    @Test
    public void testGetPlayerById_PlayerExists() {
        // Act
        Optional<Player> player = playerRepositoryCsv.getPlayerById(players.get(0).getPlayerID());

        // Assert
        assertTrue(player.isPresent());
    }

    @Test
    public void testGetPlayerById_PlayerNotFound() {
        // Act
        Optional<Player> player = playerRepositoryCsv.getPlayerById(players.get(0).getPlayerID() + "notFound");

        // Assert
        assertTrue(player.isEmpty());
    }
}
