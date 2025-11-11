package com.example.football.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer teamId;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "team_logo")
    private String teamLogo;

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
