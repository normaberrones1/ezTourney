package com.techelevator.dao;

import com.techelevator.model.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JbdcGameDao implements GameDao{
    private JdbcTemplate template;

    public JbdcGameDao(JdbcTemplate template) {
        this.template = template;
    }

    public List<Game> getAllGames(){
        List<Game> games = new ArrayList<>();

        String sql = "SELECT * FROM games";
        SqlRowSet rowSet = template.queryForRowSet(sql);
        while(rowSet.next()){
            games.add(mapRowToGame(rowSet));
        }

        return games;
    }

    public Game getGameById(int gameId){
        String sql = "SELECT * FROM games WHERE game_id = ?;";

        SqlRowSet rowSet = template.queryForRowSet(sql, gameId);
        if(rowSet.next()){
            return mapRowToGame(rowSet);
        }

        return null;
    }

    public Game getGameByName(String name){
        String sql = "SELECT * FROM games WHERE game_name = ?;";

        SqlRowSet rowSet = template.queryForRowSet(sql, name);
        if(rowSet.next()){
            return mapRowToGame(rowSet);
        }

        return null;
    }

    private Game mapRowToGame(SqlRowSet rowSet){
        Game game = new Game();
        game.setGameId(rowSet.getInt("game_id"));
        game.setGameName(rowSet.getString("game_name"));
        game.setCategoryId(rowSet.getInt("category_id"));
    }
}
