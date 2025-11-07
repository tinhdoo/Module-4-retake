package com.example.football.service;

import com.example.football.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ITeamService {
    Page<Team> getAll(Pageable pageable);

    void add(Team team);
    List<Team> getAll();

    Page<Team> searchByName(String keyword, Pageable pageable);

    Optional<Team> findById(Integer id);

}
