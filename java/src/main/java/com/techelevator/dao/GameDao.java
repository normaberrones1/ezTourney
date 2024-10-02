package com.techelevator.dao;

import com.techelevator.model.Game;

import java.util.List;

public interface GameDao {

    public Game getGameById(int gameId);

    public Game getGameByName(String gameName);

    public List<Game> getAllGames();
}
