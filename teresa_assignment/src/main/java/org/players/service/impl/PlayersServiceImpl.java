package org.players.service.impl;

import lombok.AllArgsConstructor;
import org.players.entities.Player;
import org.players.exceptions.PlayerNotFoundException;
import org.players.repositories.PlayerRepository;
import org.players.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayersServiceImpl implements PlayersService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<Player> getAllPlayers(int pageNumber, int pageSize, String sort) throws IOException {
        return playerRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sort))).toList();
    }

    @Override
    public Player getPlayerByID(String playerID) throws IOException {
        return playerRepository.findById(playerID).orElseThrow(() ->
                new PlayerNotFoundException("player with ID = " + playerID + " not found "));
    }
}
