package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTeamDao  implements TeamDao{
private  JdbcTemplate jdbcTemplate;

public JdbcTeamDao(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
}
    public Team getTeamById(int teamId){
    Team team = null;
    String sql = "SELECT team_name, captain_id, game_id, isAccepting, maxPlayers from teams WHERE team_id=?;";
    try {
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
        if (results.next()) {
            team = mapToRowSet(results);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return team;

    }

    public Team getTeamByTeamName(String teamName){
        Team team = null;
        String sql = "SELECT team_id, captain_id, game_id, isAccepting, maxPlayers from teams WHERE team_name=?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamName);
            if (results.next()) {
                team = mapToRowSet(results);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return team;
    }

    public List<Team> getTeamNames(){
    List<Team> teams = new ArrayList<>();
    String sql = "SELECT team_id, captain_id, game_id, isAccepting, max_players FROM teams ORDER BY team_name";
    try{
        SqlRowSet results = jdbcTemplate.queryForRowSet((sql);
        while(results.next()){
            Team team = mapToRowSet(results);
            teams.add(team);
        }
    }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return teams;
    }

    public void createTeam(Team newTeam) {
    String sql = "INSERT INTO team(team_name, captain_id, game_id, isAccepting, max_palyers) VALUES (?,?,?,?,?) Returning team_id;";
    try{
        int results = jdbcTemplate.queryForObject(sql,int.class);
        if (results > 0) {
            newTeam = getTeamById(results);
            newTeam.setTeamId(results);
        }
    }catch (CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
        throw new DaoException("Data integrity violation", e);
    }
    }

    public Team mapToRowSet(SqlRowSet rowSet){
    Team team = new Team();
    team.setTeamId(rowSet.getInt("team_id"));
    team.setTeamName(rowSet.getString("team_name"));
    team.setCaptainId(rowSet.getInt("captain_id"));
    team.setGameId(rowSet.getInt("game_id"));
    team.setAccepting(rowSet.getBoolean("is_Accepting"));
    team.setMaxPlayers(rowSet.getInt("max_players"));
    return team;
    }

}
