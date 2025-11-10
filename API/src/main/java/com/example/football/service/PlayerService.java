package com.example.football.service;

import com.example.football.entity.Player;
import com.example.football.repository.IPlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.rmi.StubNotFoundException;
import java.time.LocalDate;
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
    public Page<Player> getAll(Pageable pageable) { // 👈 thêm mới
        return playerRepository.findAll(pageable);
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

    @Override
    public Page<Player> searchByName(String name, PageRequest pageRequest) {
        return playerRepository.findByNameContainingIgnoreCase(name, pageRequest);
    }

    @Override
    public Page<Player> searchByNameAndDobRange(String keyword, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return playerRepository.searchByNameAndDobRange(keyword, startDate, endDate, pageable);
    }

}
