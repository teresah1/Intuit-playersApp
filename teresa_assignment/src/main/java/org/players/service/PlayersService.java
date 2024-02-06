package org.players.service;

import org.players.entities.Player;

import java.io.IOException;
import java.util.List;

public interface PlayersService {
    List<Player> getAllPlayers(int pageNumber, int pageSize, String sort) throws IOException;

    Player getPlayerByID(String playerID) throws IOException;
}
