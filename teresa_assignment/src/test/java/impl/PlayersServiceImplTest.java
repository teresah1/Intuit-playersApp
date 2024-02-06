package impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.players.entities.Player;
import org.players.exceptions.PlayerNotFoundException;
import org.players.repositories.PlayerRepository;
import org.players.service.impl.PlayersServiceImpl;
import org.springframework.data.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class PlayersServiceImplTest {

    private PlayersServiceImpl classUnderTest;
    @Mock
    private PlayerRepository playerRepository;

    @Before
    public void setUp() {
        classUnderTest = new PlayersServiceImpl(playerRepository);
    }

    @Test
    public void getAllPlayers() throws IOException {
        List<Player> players = new ArrayList<>();
        Player player1 = Player.builder().playerID("teresa").birthYear("1992")
                .birthMonth("12").birthDay("16").birthCountry("Israel").deathYear("deathYear")
                .deathMonth("deathMonth").deathCountry("deathCountry")
                .deathDay("deathDay").deathCity("deathCity")
                .deathState("deathState").nameFirst("teresa").nameLast("haddad")
                .nameGiven("teresa").weight("55").height("168")
                .bats("bats").thr("throws").debut("debut")
                .finalGame("finalGame").retroID("1202").bbrefID("8777").build();
        players.add(player1);
        Pageable pageable = PageRequest.of(1, 10);
        Page<Player> page = new PageImpl<>(players, pageable, players.size());
        Mockito.when(playerRepository.findAll(PageRequest.of(1, 10, Sort.by("playerID")))).thenReturn(page);
        List<Player> expected = classUnderTest.getAllPlayers(1, 10, "playerID");
        Assert.assertNotNull(expected);
        assertEquals(expected.get(0), player1);
    }

    @Test()
    public void getAllPlayersException() throws IOException {
        Mockito.when(playerRepository.findAll(Mockito.any(PageRequest.class))).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> classUnderTest.getAllPlayers(1, 10, "playerID"));
    }


    @Test
    public void getPlayerByID() throws IOException {
        String playerId = "teresa";
        Player player = Player.builder().playerID("teresa").birthYear("1992")
                .birthMonth("12").birthDay("16").birthCountry("Israel").deathYear("deathYear")
                .deathMonth("deathMonth").deathCountry("deathCountry")
                .deathDay("deathDay").deathCity("deathCity")
                .deathState("deathState").nameFirst("teresa").nameLast("haddad")
                .nameGiven("teresa").weight("55").height("168")
                .bats("bats").thr("throws").debut("debut")
                .finalGame("finalGame").retroID("1202").bbrefID("8777").build();
        Mockito.when(playerRepository.findById(playerId)).thenReturn(Optional.ofNullable(player));

        Player expected = classUnderTest.getPlayerByID(playerId);
        Assert.assertNotNull(expected);
        assertEquals(expected, player);
    }

    @Test()
    public void getPlayerByID_invalidPlayerID() throws IOException {
        Mockito.when(playerRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        PlayerNotFoundException exception = assertThrows(PlayerNotFoundException.class, () -> classUnderTest.getPlayerByID("invalid"));
        assertEquals("player with ID = invalid not found ", exception.getMessage());
    }
}