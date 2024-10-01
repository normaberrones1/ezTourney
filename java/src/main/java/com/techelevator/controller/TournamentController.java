package com.techelevator.controller;

import com.techelevator.dao.TournamentDao;
import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TournamentController {
    private TournamentDao dao;

    TournamentController(TournamentDao dao){
        this.dao = dao;
    }

    @RequestMapping(path="/tournaments/", method= RequestMethod.GET)
    public List<TournamentDto> getTournaments(){
        return dao.getAllTournaments();
    }
}
