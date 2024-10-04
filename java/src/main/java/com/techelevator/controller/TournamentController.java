package com.techelevator.controller;

import com.techelevator.dao.TournamentDao;
import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentDto;
import com.techelevator.model.WinLossDto;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class TournamentController {
    private TournamentDao dao;

    TournamentController(TournamentDao dao){
        this.dao = dao;
    }

    @RequestMapping(path="/tournaments/history", method= RequestMethod.GET)
    public List<TournamentDto> getTournamentHistory(){
        return dao.getAllTournamentHistory();
    }

    @RequestMapping(path="/tournaments", method= RequestMethod.GET)
    public List<TournamentDto> getActiveTournaments(){
        return dao.getAllActiveTournaments();
    }

    @RequestMapping(path="/tournaments/{id}", method=RequestMethod.GET)
    public Tournament getTournamentById(@PathVariable int id){
        return dao.getTournamentById(id);
    }

    @RequestMapping(path="/tournaments/{teamId}/wl", method=RequestMethod.GET)
    public WinLossDto getTournamentWinsAndLossesByTeam(@PathVariable int teamId){
        return dao.getTourneyWinsAndLosses(teamId);
    }

    @RequestMapping(path="/match-info/{teamId}", method = RequestMethod.GET)
    public WinLossDto getMatchWinsAndLossesByTeam(@PathVariable int teamId){
        return dao.getMatchWinLoss(teamId);
    }

    @RequestMapping(path="/is-director/{tourneyId}", method=RequestMethod.GET)
    public boolean isUserDirector(Principal principal, @PathVariable int tourneyId ){
        return dao.isUserDirector(principal, tourneyId);
    }

    @RequestMapping(path="/create-tournament", method=RequestMethod.POST)
    public Tournament createTourney(@RequestBody Tournament tourney, Principal principal){
        return dao.createTournament(tourney, principal);
    }
    @RequestMapping(path="/tournaments/{tourneyId}", method=RequestMethod.PUT)
    public Tournament updateTournament(@RequestBody Tournament tournament){
        return dao.updateTournament(tournament);
    }

    @RequestMapping(path="/tournaments/join/{tourneyId}/{teamId}", method=RequestMethod.POST)
    public boolean requestToJoinTourney(@PathVariable int tourneyId, @PathVariable int teamId){
        return dao.requestToJoinTourney(tourneyId,teamId);
    }
}
