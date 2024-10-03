package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentDto;
import com.techelevator.model.WinLossDto;
import org.springframework.dao.DataIntegrityViolationException;
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
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return null;
    }


     public boolean acceptRejectTeamJoin(int teamId, int tourneyId){
        return false;
     }


    public  Tournament createTournament(Tournament newTournament){
        String sql = "INSERT INTO tournament(tourney_name,start_date,end_date,location,entry_fee,prize_desc," +
                "tourney_desc,game_id,director_id,round,winner_id) VALUES (?,?,?,?,?,?,?,?,?,?,?) RETURNING tourney_id";
        try{
            int newTourneyId = template.queryForObject(sql,int.class,
                    newTournament.getTourneyName(),newTournament.getStartDate(),newTournament.getEndDate(),newTournament.getLocation(),
                    newTournament.getEntry_fee(),newTournament.getPrizeDesc(),newTournament.getTourneyDesc(),
                    newTournament.getGameId(),newTournament.getDirectorId(),newTournament.getRound(),newTournament.getWinner());
            if(newTourneyId > 0){
                newTournament.setTourneyId(newTourneyId);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newTournament;
    }

     public Tournament updateTournament(Tournament tournament){
        String sql= "UPDATE tournament SET game_id = ?," +
                " tourney_name = ?, start_date = ? , end_date = ?, location = ?, entry_fee = ?, prize_desc = ?, " +
                "tourney_desc= ? , round = ?, winner_id = ? WHERE tourney_id=?";
        try{
            int newTourneyId = template.update(sql,tournament.getTourneyName(),tournament.getStartDate(),
                    tournament.getEndDate(),tournament.getLocation(),tournament.getEntry_fee(),tournament.getTourneyDesc(),
                    tournament.getRound(),tournament.getWinner());
            if(newTourneyId > 0){
                tournament.setTourneyId(newTourneyId);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return tournament;
     }

    public  void nextRound(int tourneyId){

    }


    @Override
    public WinLossDto getWinsAndLosses(int teamId) {
        WinLossDto winLoss = new WinLossDto();
        String winnerSql = "SELECT COUNT(*) as wins FROM tournament " +
                "where winner_id = ?;";
        String loserSql = "SELECT COUNT(*) as losses FROM tournament " +
                "JOIN team_tourney ON tournament.tourney_id = team_tourney.tourney_id " +
                "WHERE winner_id NOTNULL AND winner_id != ? AND team_id = ?;";

        SqlRowSet winsRows = template.queryForRowSet(winnerSql, teamId);
        if(winsRows.next()){
            winLoss.setWins(winsRows.getInt("wins"));
        }
        SqlRowSet lossRows = template.queryForRowSet(loserSql, teamId, teamId);
        if(lossRows.next()){
            winLoss.setLosses(lossRows.getInt("losses"));
        }

        return winLoss;
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
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
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
