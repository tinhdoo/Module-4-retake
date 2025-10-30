package com.example.bai1.service;

import com.example.bai1.entity.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PlayerServiceImpl implements IPlayerService {
    private static final List<Player> players = new ArrayList<>();

    static {
        players.add(new Player("1", "Lionel Messi", "1987-06-24", "18 years", "Forward",
                "https://upload.wikimedia.org/wikipedia/commons/c/c1/Lionel_Messi_20180626.jpg"));
        players.add(new Player("2", "Cristiano Ronaldo", "1985-02-05", "20 years", "Forward",
                "https://upload.wikimedia.org/wikipedia/commons/8/8c/Cristiano_Ronaldo_2018.jpg"));
        players.add(new Player("3", "Kylian Mbappé", "1998-12-20", "8 years", "Forward",
                "https://upload.wikimedia.org/wikipedia/commons/a/a8/Kylian_Mbapp%C3%A9_2019.jpg"));
    }

    @Override
    public List<Player> getAll() {
        return players;
    }

    @Override
    public Player findByCode(String code) {
        return players.stream()
                .filter(p -> p.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(String code) {
        Iterator<Player> iterator = players.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getCode().equals(code)) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public void save(Player player) {
        players.add(player);
    }
}
