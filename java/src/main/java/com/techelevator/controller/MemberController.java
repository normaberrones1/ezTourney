package com.techelevator.controller;

import com.techelevator.dao.MemberDao;
import com.techelevator.model.TeamDto;
import com.techelevator.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class MemberController {

    private MemberDao dao;

    MemberController(MemberDao dao){
        this.dao = dao;
    }

    //get member name and id by id
    @RequestMapping(path="/member/{id}", method= RequestMethod.GET)
    public UserDto getCaptain(@PathVariable int id){
        return dao.getMemberById(id);
    }

    //Get all members on a team
    @RequestMapping(path="/team/{id}/members", method = RequestMethod.GET)
    public List<UserDto> getMembersOnTeam(@PathVariable int id){
        return dao.getMembersByTeamId(id);
    }

    //get a users own teams
    @RequestMapping(path="/myteams/", method = RequestMethod.GET)
    public List<TeamDto> viewMyTeams(Principal principal){
        return dao.getMyTeams(principal);
    }

    @RequestMapping(path="/am-team-captain/{teamId}", method = RequestMethod.GET)
    public boolean amTeamCaptain(Principal principal, @PathVariable int teamId){
        return dao.isCurrentUserTeamCaptain(principal, teamId);
    }
}
