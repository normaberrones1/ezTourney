package com.techelevator.dao;

import com.techelevator.model.Team;

import java.util.List;

public interface TeamDao {
    public Team getTeamById(int teamId);
    public Team getTeamByTeamName(String teamName);

    public List<Team> getTeamNames();

}
