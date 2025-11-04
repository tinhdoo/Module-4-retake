package com.example.football.service;

import com.example.football.entity.Player;

import java.util.List;
import java.util.Optional;

public interface IPlayerService {
    List<Player> getAll();
    Optional<Player> findByCode(Integer code);
    void delete(Integer code);

    void save(Player player);
}
