package com.example.playerapiservice.api.controllers;

import com.example.playerapiservice.AbstractPlayerApiServiceTest;
import com.example.playerapiservice.entities.Player;
import com.example.playerapiservice.services.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import static org.hamcrest.Matchers.is;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlayerControllerTest extends AbstractPlayerApiServiceTest {
    @MockBean
    private PlayerService playerService;

    @Before
    public void setUp() {
    }

    @Test
    public void testGetAllPlayers() throws Exception {
        // Arrange
        List<Player> expectedPlayers = setupPlayers();
        Page<Player> playerPage = new PageImpl<>(expectedPlayers);
        when(playerService.getAllPlayers(any(Pageable.class))).thenReturn(playerPage);

        // Act and Assert
        mockMvc.perform(get("/api/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(expectedPlayers.size())));
    }

    @Test
    public void testGetPlayerById() throws Exception {
        // Arrange
        String playerID = "testPlayerID";
        Player expectedPlayer = setupPlayer(playerID);
        when(playerService.getPlayerById(playerID)).thenReturn(expectedPlayer);

        // Act and Assert
        mockMvc.perform(get("/api/players/{playerID}", playerID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerID", is(expectedPlayer.getPlayerID())))
                .andExpect(jsonPath("$.nameFirst", is(expectedPlayer.getNameFirst())));
    }

}
