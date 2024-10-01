package com.techelevator.controller;

import com.techelevator.dao.TournamentDao;
import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentDto;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path="/tournaments/{id}", method=RequestMethod.GET)
    public Tournament getTournamentById(@PathVariable int id){
        return dao.getTournamentById(id);
    }
}
