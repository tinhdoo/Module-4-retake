package com.example.bai1.service;

import com.example.bai1.entity.Player;
import java.util.List;

public interface IPlayerService {
    List<Player> getAll();
    Player findByCode(String code);
    void delete(String code);
}
