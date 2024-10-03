package com.techelevator.dao;

import com.techelevator.model.AcceptRejectTeamDto;
import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;

import java.security.Principal;
import java.util.List;

public interface TeamDao {

    List<Team> getTeamNames();

    Team getTeamById(int teamId);

    Team getTeamByTeamName(String teamName);

    Team createTeam(Team newTeam, Principal principal);

    Team updateTeam(Team team);

    List<TeamDto> getAllTeams();

    boolean deleteTeamById(int teamId);

    Team getTeamByGameId(int gameId);

    boolean requestTeamJoin(Principal principal, int teamId);

    void acceptRequest(AcceptRejectTeamDto acceptReject);

    void rejectRequest(AcceptRejectTeamDto acceptReject);
}

