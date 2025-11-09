package com.example.football.repository;

import com.example.football.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IPlayerRepository extends JpaRepository<Player, Integer> {
    Page<Player> findByNameContainingIgnoreCase(String name, Pageable pageable);
    @Query("SELECT p FROM Player p " +
            "WHERE (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:startDate IS NULL OR p.dob >= :startDate) " +
            "AND (:endDate IS NULL OR p.dob <= :endDate)")
    Page<Player> searchByNameAndDobRange(@Param("keyword") String keyword,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate,
                                         Pageable pageable);
    long countByStatus(String status);

}
