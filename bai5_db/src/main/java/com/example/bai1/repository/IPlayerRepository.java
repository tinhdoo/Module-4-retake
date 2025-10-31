package com.example.bai1.repository;

import com.example.bai1.entity.Player;
import java.util.List;

public interface IPlayerRepository {
    List<Player> findAll();
    boolean add(Player player);
    Player findByCode(String code);
    void delete(Player player);
}
