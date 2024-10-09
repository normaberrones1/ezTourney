package com.techelevator.controller;

import com.techelevator.dao.TournamentDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class TournamentController {
    private TournamentDao dao;

    TournamentController(TournamentDao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/tournaments/history", method = RequestMethod.GET)
    public List<TournamentDto> getTournamentHistory() {
        return dao.getAllTournamentHistory();
    }

    @RequestMapping(path = "/tournaments", method = RequestMethod.GET)
    public List<TournamentDto> getActiveTournaments() {
        return dao.getAllActiveTournaments();
    }

    @RequestMapping(path = "/tournaments/{id}", method = RequestMethod.GET)
    public Tournament getTournamentById(@PathVariable int id) {
        return dao.getTournamentById(id);
    }

    @RequestMapping(path = "/tournaments/{teamId}/wl", method = RequestMethod.GET)
    public WinLossDto getTournamentWinsAndLossesByTeam(@PathVariable int teamId) {
        return dao.getTourneyWinsAndLosses(teamId);
    }

    @RequestMapping(path = "/match-info/{teamId}", method = RequestMethod.GET)
    public WinLossDto getMatchWinsAndLossesByTeam(@PathVariable int teamId) {
        return dao.getMatchWinLoss(teamId);
    }

    @RequestMapping(path = "/is-director/{tourneyId}", method = RequestMethod.GET)
    public boolean isUserDirector(Principal principal, @PathVariable int tourneyId) {
        return dao.isUserDirector(principal, tourneyId);
    }

    @RequestMapping(path = "/create-tournament", method = RequestMethod.POST)
    public Tournament createTourney(@RequestBody Tournament tourney, Principal principal) {
        return dao.createTournament(tourney, principal);
    }

    @RequestMapping(path = "/tournaments/{tourneyId}", method = RequestMethod.PUT)
    public Tournament updateTournament(@RequestBody Tournament tournament) {
        return dao.updateTournament(tournament);
    }


    @RequestMapping(path = "/tournaments/{tourneyId}/teams", method = RequestMethod.GET )
    public List<TourneyTeamDto> getTourneyTeams(@PathVariable int tourneyId){
        return dao.getTourneyTeams(tourneyId);
    }

    @RequestMapping(path = "/tournaments/{tourneyId}/accept-team/{teamId}", method = RequestMethod.PATCH)
    public boolean acceptTourneyTeam( @PathVariable int tourneyId,@PathVariable int teamId,Principal principal) {
        if (dao.isUserDirector(principal, tourneyId)) {
            return dao.acceptTourneyTeam(teamId, tourneyId);
        } else {
            throw new AccessDeniedException("Only tourney director can accept a team");
        }
    }

    @RequestMapping(path = "/tournaments/{id}/view", method = RequestMethod.GET)
    public Tournament getTournamentViewById(@PathVariable int id) {
        return dao.getTourneyDetailsViewById(id);
    }


    @RequestMapping(path="tournaments/filter", method=RequestMethod.GET)
    public List<TournamentDto> getFilteredTournaments(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate) {

        return dao.getTournamentsByFilters(status, startDate, endDate);
    }

    @RequestMapping(path="tournaments/is-director/{directorId}/filter", method=RequestMethod.GET)
    public List<TournamentDto> getTournamentsForDirectors(
            @PathVariable Integer directorId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate) {


        return dao.getTournamentsForDirectors(directorId, status, startDate, endDate);
    }


    @RequestMapping(path="/tournaments/join/{tourneyId}/{teamId}", method=RequestMethod.POST)
    public boolean requestToJoinTourney(@PathVariable int tourneyId, @PathVariable int teamId){
        return dao.requestToJoinTourney(tourneyId,teamId);
    }

    @RequestMapping(path = "/tournaments/{tourneyId}/users", method = RequestMethod.GET )
    public List<TourneyUserDto> getTourneyUsers(@PathVariable int tourneyId){
        return dao.getTourneyUsers(tourneyId);
    }

    @RequestMapping(path = "/tournaments/{tourneyId}/accept-user/{userId}", method = RequestMethod.PATCH)
    public boolean acceptTourneyUser( @PathVariable int tourneyId,@PathVariable int userId,Principal principal) {
        if (dao.isUserDirector(principal, tourneyId)) {
            return dao.acceptTourneyUser(userId, tourneyId);
        } else {
            throw new AccessDeniedException("Only tourney director can accept a team");
        }
    }

}
