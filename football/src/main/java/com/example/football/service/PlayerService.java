package com.example.football.service;

import com.example.football.entity.Player;
import com.example.football.repository.IPlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.StubNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> findByCode(Integer code) {
        return playerRepository.findById(code);
    }

    @Transactional
    @Override
    public void delete(Integer code) {
        Player player = playerRepository.findById(code).orElse(null);
        if (player != null){
            playerRepository.delete(player);
        }else {
            System.out.println("Không tìm thấy cầu thủ với mã: " + code);
        }
    }

    @Transactional
    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }
}
