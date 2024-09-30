package com.techelevator.controller;

import com.techelevator.dao.TeamDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.TeamDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class TeamController {

    private TeamDao teamDao;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/teams", method = RequestMethod.POST)
    public void teams(@RequestBody TeamDto newTeam) {
        try {
            if(teamDao.getTeamByTeamName(newTeam.getTeamName()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team already exists.");
            } else {
                teamDao.createTeam(newTeam);
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Team registration error.");
        }

    }


    //GET all teams
    @RequestMapping(path = "/teams", method = RequestMethod.GET)


    //GET teams by ID
    @RequestMapping(path = "/teams/{teamId}", method = RequestMethod.GET)


    //PUT teams by ID
    @RequestMapping(path = "/teams/{teamId}", method = RequestMethod.PUT)


    //DELETE teams by ID
    @RequestMapping(path = "/teams/{teamId}", method = RequestMethod.DELETE)


    //GET teams by game ID
    @RequestMapping(path = "/teams/game/{gameId}", method = RequestMethod.GET)


    //GET teams by captain ID
    @RequestMapping(path = "/teams/captain/{captainId}", method = RequestMethod.GET)


    //PATCH accept/reject teams
    @RequestMapping(path = "/teams/{id}/accepting", method = RequestMethod.PATCH)
}
