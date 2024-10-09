package com.techelevator.dao;

import com.techelevator.model.BracketDto;
import com.techelevator.model.MatchDto;
import com.techelevator.model.ScoreDto;

import java.util.List;

public interface TeamBracketDao {

    void saveFirstRound(int tourneyId, List<String> bracketData);

    List<MatchDto> advanceNextRound(int tourneyId);

    boolean saveScore(ScoreDto scores, int tourneyId);
}
