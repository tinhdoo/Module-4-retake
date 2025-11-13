package com.example.football.service;

import com.example.football.entity.Team;
import com.example.football.repository.ITeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private ITeamRepository teamRepository;

    @Override
    public Page<Team> getAll(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    @Override
    public void add(Team team) {
        teamRepository.save(team);
    }
    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Page<Team> searchByName(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return teamRepository.findAll(pageable);
        }
        return teamRepository.findByTeamNameContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public Optional<Team> findById(Integer id) {
        return teamRepository.findById(id);
    }
}
