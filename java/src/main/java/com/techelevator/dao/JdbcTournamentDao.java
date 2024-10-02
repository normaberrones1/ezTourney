package com.techelevator.dao;


import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentDto;
import com.techelevator.model.WinLossDto;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTournamentDao implements TournamentDao{

    private JdbcTemplate template;

    private String dtoSelect = "SELECT tourney_id, tourney_name, entry_fee, tourney_desc, " +
            "start_date, username ";

    JdbcTournamentDao(JdbcTemplate template){
        this.template = template;
    }

    //PAST AND PRESENT
    public List<TournamentDto> getAllTournamentHistory(){
        List<TournamentDto> tournaments = new ArrayList<>();
        String sql = dtoSelect + " FROM TOURNAMENT JOIN users ON director_id = user_id;";

        try {
            SqlRowSet rowSet = template.queryForRowSet(sql);
            while(rowSet.next()){
                tournaments.add(mapRowToTournamentDto(rowSet));
            }
        }catch(CannotGetJdbcConnectionException e){
        }
        return tournaments;
    }

    public List<TournamentDto> getAllActiveTournaments(){
        List<TournamentDto> tournaments = new ArrayList<>();
        String sql = dtoSelect + " FROM TOURNAMENT JOIN users ON director_id = user_id " +
                "WHERE current_timestamp < start_date;";

        try {
            SqlRowSet rowSet = template.queryForRowSet(sql);
            while(rowSet.next()){
                tournaments.add(mapRowToTournamentDto(rowSet));
            }
        }catch(CannotGetJdbcConnectionException e){
        }
        return tournaments;
    }

    public Tournament getTournamentById(int id){
        String sql = "SELECT * FROM tournament WHERE tourney_id = ?;";

        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, id);
            if (rowSet.next()) {
                return mapRowToTournament(rowSet);
            }
        }catch(CannotGetJdbcConnectionException e){

        }

        return null;
    }


     public boolean acceptRejectTeamJoin(int teamId, int tourneyId){
        return false;
     }
    public  Tournament createTournament(Tournament newTournament){
        return null;
    }

    public  void nextRound(int tourneyId){

    }

    @Override
    public WinLossDto getWinsAndLosses(int teamId) {
        //this method is all under the pretext that "tourney_desc" means "decision," and equals either 'win' or 'loss'
        String sql = "SELECT COUNT(CASE WHEN t.tourney_desc = 'win' THEN 1 END) AS wins, " +
                             "COUNT(CASE WHEN t.tourney_desc = 'loss' THEN 1 END) AS losses " +
                      "FROM team_tourney tt " +
                        "JOIN tournament t ON tt.team_id = t.team_id AND tt.tourney_id = t.tourney_id " +
                        "WHERE tt.team_id = ?;";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, teamId);

            if(rowSet.next()) {
                WinLossDto winLossDto = new WinLossDto();
                winLossDto.setWins(rowSet.getInt("wins"));
                winLossDto.setLosses(rowSet.getInt("losses"));
                return winLossDto;
            }
        } catch (CannotGetJdbcConnectionException e) {

        }
        return null;
    }

    public List<TournamentDto> getAllCurrentTournaments(int teamId) {

        List<TournamentDto> currentTournaments = new ArrayList<>();
        String sql = "SELECT is_complete FROM " +
                "team_tourney tt " +
                "JOIN tournament t ON tt.team_id = t.team_id AND tt. tourney_id = t.tourney_id " +
                "WHERE tt.team_id = ?;";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, teamId);

            while(rowSet.next()){
                currentTournaments.add(mapRowToTournamentDto(rowSet));
            }
        } catch (CannotGetJdbcConnectionException e) {

        }
        return currentTournaments;
    }

    private TournamentDto mapRowToTournamentDto(SqlRowSet rowSet){
        TournamentDto dto = new TournamentDto();
        dto.setTourneyId(rowSet.getInt("tourney_id"));
        dto.setTourneyName(rowSet.getString("tourney_name"));
        dto.setEntryFee(BigDecimal.valueOf(rowSet.getInt("entry_fee")));
        dto.setTourneyDesc(rowSet.getString("tourney_desc"));
        dto.setStartDate(rowSet.getDate("start_date"));
        dto.setDirectorName(rowSet.getString("username"));
        return dto;
    }

    private Tournament mapRowToTournament(SqlRowSet rowSet){
        Tournament tournament = new Tournament();
        tournament.setTourneyId(rowSet.getInt("tourney_id"));
        tournament.setTourneyName(rowSet.getString("tourney_name"));
        tournament.setStartDate(rowSet.getDate("start_Date"));
        tournament.setEndDate(rowSet.getDate("end_date"));
        tournament.setLocation(rowSet.getString("location"));
        tournament.setEntry_fee(rowSet.getInt("entry_fee"));
        tournament.setPrizeDesc(rowSet.getString("prize_desc"));
        tournament.setGameId(rowSet.getInt("game_id"));
        tournament.setDirectorId(rowSet.getInt("director_id"));
        tournament.setRound(rowSet.getInt("round"));
        tournament.setWinner(rowSet.getInt("winner_id"));

        return tournament;
    }
}
