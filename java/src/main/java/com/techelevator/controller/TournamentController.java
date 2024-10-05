package com.techelevator.controller;

import com.techelevator.dao.TournamentDao;
import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentDto;
import com.techelevator.model.TourneyTeamDto;
import com.techelevator.model.WinLossDto;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.time.LocalDate;
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

<<<<<<< HEAD
    @RequestMapping(path = "/tournaments/{tourneyId}/teams", method = RequestMethod.GET )
    public List<TourneyTeamDto> getTourneyTeams(@PathVariable int tourneyId){
        return dao.getTourneyTeams(tourneyId);
    }

    @RequestMapping(path = "/tournaments/{tourneyId}/accept-team/{teamId}", method = RequestMethod.PATCH)
    public boolean acceptTeam( @PathVariable int tourneyId,@PathVariable int teamId,Principal principal) {
        if (dao.isUserDirector(principal, tourneyId)) {
            return dao.acceptTeam(teamId, tourneyId);
        } else {
            throw new AccessDeniedException("Only tourney director can accept a team");
        }
    }

    @RequestMapping(path = "/tournaments/{id}/view", method = RequestMethod.GET)
    public Tournament getTournamentViewById(@PathVariable int id) {
        return dao.getTourneyDetailsViewById(id);
    }
=======

    @RequestMapping(path="tournaments/filter", method=RequestMethod.GET)
    public List<TournamentDto> getFilteredTournaments(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate) {

        return dao.getTournamentsByFilters(status, startDate, endDate);
    }


    @RequestMapping(path="/tournaments/join/{tourneyId}/{teamId}", method=RequestMethod.POST)
    public boolean requestToJoinTourney(@PathVariable int tourneyId, @PathVariable int teamId){
        return dao.requestToJoinTourney(tourneyId,teamId);
    }

>>>>>>> 5e61e9d8924453074228fbd48656fb086175bd4f
}
