package org.players.controller;

import lombok.AllArgsConstructor;
import org.players.entities.Player;
import org.players.exceptions.PlayerNotFoundException;
import org.players.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PlayersController {

    @Autowired
    private PlayersService playersService;

    @GetMapping(value = "/players", produces = {"application/json"})
    public ResponseEntity<Object> getAllPlayers(@RequestParam(name = "pagesNumber", required = false, defaultValue = "0") Integer pagesNumber,
                                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                @RequestParam(name = "sort", required = false, defaultValue = "playerID") String sort) {
        try {
            List<Player> allPlayers = playersService.getAllPlayers(pagesNumber, pageSize, sort);
            if (allPlayers.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(allPlayers);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/players/{playerID}", produces = {"application/json"})
    public ResponseEntity<Object> getPlayerById(@PathVariable("playerID") String playerId) {
        try {
            Player player = playersService.getPlayerByID(playerId);
            return ResponseEntity.ok(player);
        } catch (PlayerNotFoundException playerNotFoundException) {
            return ResponseEntity.badRequest().body(playerNotFoundException.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
