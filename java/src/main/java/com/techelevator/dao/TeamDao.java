package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;

public interface TeamDao {

    Team getTeamById(int teamId);

    Team getTeamByTeamName(String teamName);

    void createTeam(TeamDto newTeam);

}

