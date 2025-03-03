package com.techelevator.dao;

import com.techelevator.model.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

public interface TournamentDao {

    List<TournamentDto> getAllTournamentHistory();

    List<TournamentDto> getTournamentsForDirectors(Integer directorId, String status, Date startDate, Date endDate);


    Tournament getTournamentById(int id);

    List<TournamentDto> getAllActiveTournaments();

    List<TournamentDto> getTournamentsByFilters(String status, Date startDate, Date endDate);

    /*
    INPUTS: Team id

    have outputs count each tournament a team has won, and
    for each tournament a team has lost.
    give output as a custom DTO object that holds 2 ints.
    those ints are Wins and Losses

     */
    WinLossDto getTourneyWinsAndLosses(int teamId);

    /*
    With a teamId, get all tournaments they're participating in. The output will be a
    tournamentDto object. a function called mapRowToTournamentDto with a SqlRowSet input
    will make tournamentDto objects for you
    Give it a proper path on TournamentController.java
     */
    List<TournamentDto> getAllCurrentTournaments(int teamId);

    /*
    Given All content besides TournamentId, create a new tournament.
    For testing purposes with Postman, give it a receipt that is the newly
    created tournament object. Assume the tournament object is valid
    Give it a proper path on TournamentController.java
     */
    Tournament createTournament(Tournament newTournament, Principal principal);

    /*
    If current user is the tournament director, allow them to update team_tourney to
    accept a team to the tournament.
    if accepted updated isAccepted to true
    if rejected delete the team from team_tourney
    return true if successful or false if not
    Give it a proper path on TournamentController.java
     */
    boolean acceptTourneyTeam(int teamId, int tourneyId);

    /*
    Update the tournament table to go to the next round.
    this method can be void
    Give it a proper path on TournamentController.java
     */
    void nextRound(int tourneyId);


    Tournament updateTournament(Tournament tournament);



    WinLossDto getMatchWinLoss(int teamId);

    boolean isUserDirector(Principal principal, int tourneyId);

    List<TourneyTeamDto> getTourneyTeams(int tourneyId);

    Tournament getTourneyDetailsViewById(int tourneyId);

    boolean teamRequestToJoinTourney(int tourneyId, int teamId);

    boolean userRequestToJoinTourney(int tourneyId, Principal principal);

    boolean acceptTourneyUser(int userId, int tourneyId);

    List<TourneyUserDto> getTourneyUsers(int tourneyId);

    List<UserDto> getTourneyDirectors(int tourneyId);
}

