package com.techelevator.dao;

import com.techelevator.model.Game;

public interface GameDao {

    public Game getGameById(int gameId);

    public Game getGameByName(String gameName);

}
