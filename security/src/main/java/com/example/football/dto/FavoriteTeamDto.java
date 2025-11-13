package com.example.football.dto;

import com.example.football.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class FavoriteTeamDto {

    private Map<Integer, Player> players = new HashMap<>();

    public FavoriteTeamDto() {
    }

    public Map<Integer, Player> getPlayers() {
        return players;
    }

    private boolean checkItemInFavorite(Integer code) {
        return players.containsKey(code);
    }

    public void addPlayer(Player player) {
        if (!checkItemInFavorite(player.getCode())) {
            players.put(player.getCode(), player);
        }
    }

    public void removePlayer(Integer code) {
        if (checkItemInFavorite(code)) {
            players.remove(code);
        }
    }

    public int countPlayers() {
        return players.size();
    }
}