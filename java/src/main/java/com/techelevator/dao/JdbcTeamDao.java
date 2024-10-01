package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.ArrayList;

import java.util.List;

@Component
@CrossOrigin
public class JdbcTeamDao implements TeamDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTeamDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Team getTeamById(int teamId) {
        Team team = null;
        String sql = "SELECT team_name, captain_id, game_id, isAccepting, max_Players FROM teams WHERE team_id=?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
            if (results.next()) {
                team = mapToRowSet(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return team;

    }

    public Team getTeamByTeamName(String teamName) {
        Team team = null;
        String sql = "SELECT team_id, captain_id, game_id, isAccepting, max_Players from teams WHERE team_name=?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamName);
            if (results.next()) {
                team = mapToRowSet(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return team;
    }

    @Override
    public Team updateTeam(Team team) {
        String sql = "UPDATE team SET game_id = ?, " + "team_name = ? , captain_id = ? , " +
                "isAccepting = ?, max_players =? WHERE team_id=?;";
        try {
            int numOfRows = jdbcTemplate.update(sql, team.getGameId(), team.getTeamName(),
                    team.getCaptainId(), team.isAccepting(), team.getTeamId());
            if (numOfRows == 0) {
                throw new DataIntegrityViolationException("No team exist with teamId =" + team.getTeamId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
        return team;

    }



    @Override
    public boolean deleteTeamById(int teamId) {

        return false;

    }

    public List<Team> getTeamNames() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT team_id, captain_id, game_id, isAccepting, max_players FROM teams ORDER BY team_name";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Team team = mapToRowSet(results);
                teams.add(team);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return teams;
    }

    public void createTeam(Team newTeam) {
        String sql = "INSERT INTO team(team_name, captain_id, game_id, isAccepting, max_players) VALUES (?,?,?,?,?) Returning team_id;";
        try {
            int results = jdbcTemplate.queryForObject(sql, int.class);
            if (results > 0) {
                newTeam = getTeamById(results);
                newTeam.setTeamId(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public Team getTeamByGameId(int gameId) {
        Team team = null;
        String sql = "SELECT team_id, team_name, captain_id, isAccepting, max_players WHERE game_id=?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
            if (results.next()) {
                team = mapToRowSet(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return team;
    }

    public List<TeamDto> getAllTeams() {
        List<TeamDto> allteams = new ArrayList<>();
        String sql = "SELECT team_name, captain_id, team_id FROM teams;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                TeamDto teamDto = mapRowSetToTeamDto(results);
                allteams.add(teamDto);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return allteams;
    }

    private TeamDto mapRowSetToTeamDto(SqlRowSet rowSet) {
        TeamDto teamDto = new TeamDto();
        //Todo: Add logic to fill teamDto
        teamDto.setCaptainId(rowSet.getInt("captain_Id"));
        teamDto.setTeamName(rowSet.getString("team_Name"));
        teamDto.setTeamId(rowSet.getInt("team_id"));
        return teamDto;
    }

    public Team mapToRowSet(SqlRowSet rowSet) {
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

