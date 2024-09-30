package com.techelevator.dao;

import com.techelevator.model.Team;

public interface TeamDao {
    public Team getTeamById(int teamId);
    public Team getTeamByTeamName(String teamName);
}
