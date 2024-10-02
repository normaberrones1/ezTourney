package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;

import java.util.List;

public interface TeamDao {

    List<Team> getTeamNames();

    Team getTeamById(int teamId);

    Team getTeamByTeamName(String teamName);

    Team createTeam(Team newTeam);

    Team updateTeam(Team team);

    List<TeamDto> getAllTeams();

    boolean deleteTeamById(int teamId);

    Team getTeamByGameId(int gameId);

}

