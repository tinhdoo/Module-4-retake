package com.example.football.repository;

import com.example.football.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeamRepository extends JpaRepository<Team, Integer> {
    Page<Team> findByTeamNameContainingIgnoreCase(String teamName, Pageable pageable);
}
