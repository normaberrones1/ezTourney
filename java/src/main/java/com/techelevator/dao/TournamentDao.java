package com.techelevator.dao;

import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentDto;

import java.util.List;

public interface TournamentDao {

    List<TournamentDto> getAllTournaments();

    Tournament getTournamentById(int id);


}
