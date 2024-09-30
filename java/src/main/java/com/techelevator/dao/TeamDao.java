package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;

import java.util.List;

public interface TeamDao {

    List<Team> getTeamNames();

    Team getTeamById(int teamId);

    Team getTeamByTeamName(String teamName);

    void createTeam(Team newTeam);


    List<TeamDto> getAllTeams();

    Team updateTeam(Team team);


    boolean deleteTeamById(int teamId);
}

