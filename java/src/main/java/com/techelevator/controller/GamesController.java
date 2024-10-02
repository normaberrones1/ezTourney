package com.techelevator.controller;


import com.techelevator.dao.JdbcGameDao;
import com.techelevator.model.Game;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GamesController {
    private JdbcGameDao dao;

    public GamesController(JdbcGameDao dao){
        this.dao = dao;
    }

    @RequestMapping(path="/allgames", method= RequestMethod.GET)
    public List<Game> getAllGames(){
        return dao.getAllGames();
    }

    @RequestMapping(path="/allgames/name={name}", method=RequestMethod.GET)
    public Game getGameByName(@PathVariable String name){
        return dao.getGameByName(name);
    }

    @RequestMapping(path="/allgames/id={id}", method=RequestMethod.GET)
    public Game getGameById(@PathVariable int id){
        return dao.getGameById(id);
    }


}
