package com.example.playerapiservice.services;

import com.example.playerapiservice.AbstractPlayerApiServiceTest;
import com.example.playerapiservice.entities.Player;
import com.example.playerapiservice.exceptions.ResourceNotFoundException;
import com.example.playerapiservice.repositories.PlayerRepositoryCsv;
import com.example.playerapiservice.validators.PlayerValidator;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PlayerServiceTest extends AbstractPlayerApiServiceTest {
    @Autowired
    PlayerRepositoryCsv playerRepositoryCsv;
    @Autowired
    private PlayerValidator playerValidator;
    @Autowired
    private PlayerService playerService;

    @Before
    public void setUp() {
    }

    @Test
    public void testGetAllPlayers() {
        // Act
        Page<Player> actualPlayers = playerService.getAllPlayers(PageRequest.of(0, 5));

        // Assert
        Assert.assertNotNull(actualPlayers);
        Assert.assertTrue(CollectionUtils.isNotEmpty(actualPlayers.getContent()));
        Assert.assertEquals(5, actualPlayers.getContent().size());
    }

    @Test
    public void testGetPlayerById_ValidPlayerID() {
        // Arrange
        Page<Player> actualPlayers = playerService.getAllPlayers(PageRequest.of(0, 5));
        Assert.assertNotNull(actualPlayers);
        String validPlayerID = actualPlayers.getContent().get(0).getPlayerID();

        // Act
        Player actualPlayer = playerService.getPlayerById(validPlayerID);

        // Assert
        assertEquals(validPlayerID, actualPlayer.getPlayerID());
    }

    @Test
    public void testGetPlayerById_playerDoesNotExistByTheProvidedId() {
        // Arrange
        String invalidPlayerID = "invalidID";

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> playerService.getPlayerById(invalidPlayerID));
    }
}
