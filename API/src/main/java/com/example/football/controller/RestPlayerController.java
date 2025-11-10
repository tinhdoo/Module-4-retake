package com.example.football.controller;

import com.example.football.entity.Player;
import com.example.football.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/api/players")
public class RestPlayerController {

    @Autowired
    private IPlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getAll() {
        List<Player> playerList = playerService.getAll();
        if (playerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable("id") int id) {
        Optional<Player> playerOpt = playerService.findByCode(id);
        if (playerOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playerOpt.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> add(@RequestBody Player player) {
        playerService.save(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> update(@PathVariable("id") int id,
                                         @RequestBody Player updatedPlayer) {
        Optional<Player> playerOpt = playerService.findByCode(id);
        if (playerOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedPlayer.setCode(id);
        playerService.save(updatedPlayer);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        Optional<Player> playerOpt = playerService.findByCode(id);
        if (playerOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
