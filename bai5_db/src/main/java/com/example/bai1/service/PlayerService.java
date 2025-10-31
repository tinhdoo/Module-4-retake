package com.example.bai1.service;

import com.example.bai1.entity.Player;
import com.example.bai1.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findByCode(String code) {
        return playerRepository.findByCode(code);
    }

    @Transactional
    @Override
    public void delete(String code) {
        Player player = playerRepository.findByCode(code);
        if (player != null) {
            playerRepository.delete(player);
        }
    }

    @Transactional
    @Override
    public void save(Player player) {
        playerRepository.add(player);
    }
}
