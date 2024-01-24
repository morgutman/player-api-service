package com.example.playerapiservice;

import com.example.playerapiservice.entities.Player;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {PlayerApiServiceApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public abstract class AbstractPlayerApiServiceTest {
    @Autowired
    protected MockMvc mockMvc;

    protected List<Player> setupPlayers() {
        return List.of(setupPlayer("1"), setupPlayer("2"), setupPlayer("3"));
    }

    protected Player setupPlayer(String playerId) {
        Player player = new Player();
        player.setPlayerID(playerId);
        player.setBirthYear(1999);
        player.setBirthDay(9);
        player.setBirthMonth(9);
        player.setNameFirst("someFirstName");
        player.setNameLast("someLastName");
        return player;
    }
}
