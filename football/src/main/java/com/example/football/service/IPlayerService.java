package com.example.football.service;

import com.example.football.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPlayerService {
    List<Player> getAll();
    Page<Player> getAll(Pageable pageable);

    Optional<Player> findByCode(Integer code);
    void delete(Integer code);

    void save(Player player);
    Page<Player> searchByName(String name, PageRequest pageRequest);
    Page<Player> searchByNameAndDobRange(String keyword, LocalDate startDate, LocalDate endDate, Pageable pageable);

}
