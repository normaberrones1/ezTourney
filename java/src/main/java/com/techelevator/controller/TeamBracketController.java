package com.techelevator.controller;


import com.techelevator.dao.JdbcTeamBracketDao;
import com.techelevator.dao.TeamBracketDao;
import com.techelevator.model.BracketDto;
import com.techelevator.model.MatchDto;
import com.techelevator.model.ScoreDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TeamBracketController {

    private TeamBracketDao teamBracketDao;

    public TeamBracketController(TeamBracketDao teamBracketDao){
        this.teamBracketDao = teamBracketDao;
    }

    @RequestMapping(path="/tournaments/{tourneyId}/brackets/create", method = RequestMethod.POST)
    public void createRound1(@RequestBody List<String> bracketData, @PathVariable int tourneyId){
        teamBracketDao.saveFirstRound(tourneyId, bracketData);
    }

    @RequestMapping(path ="/tournaments/{tourneyId}/nextRound", method=RequestMethod.GET)
    public List<MatchDto> nextRound(@PathVariable int tourneyId){
        return teamBracketDao.advanceNextRound(tourneyId);
    }

    @RequestMapping(path="/tournaments/{tourneyId}/brackets/save-score", method=RequestMethod.POST)
    public boolean saveScores(@RequestBody ScoreDto scores, @PathVariable int tourneyId){
        return teamBracketDao.saveScore(scores, tourneyId);
    }
}
