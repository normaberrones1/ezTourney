package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.AcceptRejectTeamDto;
import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;


import java.security.Principal;
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

        String sql = "SELECT team_id, team_name, captain_id, game_id, isaccepting, max_players FROM teams WHERE team_id=?;";

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
        String sql = "SELECT team_id, team_name, captain_id, game_id, isaccepting, max_players from teams WHERE team_name=?;";
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
        String sql = "UPDATE teams SET game_id = ?, " +
                "team_name = ? , captain_id = ? , " +
                "isaccepting = ?, max_players =? " +
                " WHERE team_id=?;";
        try {
            int numOfRows = jdbcTemplate.update(sql,
                    team.getGameId(), team.getTeamName(),
                    team.getCaptainId(), team.isAccepting(),
                    team.getMaxPlayers(), team.getTeamId());
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

    public void acceptRequest(AcceptRejectTeamDto acceptReject){
        String sql = "UPDATE team_users SET accepted = 'true' " +
                "WHERE user_id = ? AND team_id = ?;";
        jdbcTemplate.update(sql, acceptReject.getUserId(), acceptReject.getTeamId());

    }

    @Override
    public void rejectRequest(AcceptRejectTeamDto acceptReject) {
        String sql = "DELETE FROM team_users WHERE " +
                "user_id = ? AND team_id = ?;";

        jdbcTemplate.update(sql, acceptReject.getUserId(), acceptReject.getTeamId());
    }

    @Override
    public boolean deleteTeamById(int teamId) {

        return false;

    }

    public List<Team> getTeamNames() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT team_id, team_name, captain_id, game_id, isaccepting, max_players FROM teams";
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

    public Team createTeam(Team newTeam, Principal principal) {

        String sql = "INSERT INTO teams (team_name, captain_id, game_id, isaccepting, max_players) VALUES (?,?,?,?,?) RETURNING team_id";
        String sql2 = "SELECT user_id FROM users WHERE username = ?;";
        String sql3 = "INSERT INTO team_users (team_id, user_id, accepted) VALUES (?,?,true)";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql2, principal.getName());
            if(rowSet.next()) {
                newTeam.setCaptainId(rowSet.getInt("user_id"));
            }

            int newTeamId = jdbcTemplate.queryForObject(sql, int.class,
                    newTeam.getTeamName(), newTeam.getCaptainId(), newTeam.getGameId(), newTeam.isAccepting(), newTeam.getMaxPlayers());
            if (newTeamId > 0) {
                newTeam.setTeamId(newTeamId);
            }

            jdbcTemplate.update(sql3, newTeam.getTeamId(), newTeam.getCaptainId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newTeam;
    }

    @Override
    public Team getTeamByGameId(int gameId) {
        Team team = null;
        String sql = "SELECT team_id, team_name, captain_id, isaccepting, max_players WHERE game_id=?;";
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
        String sql = "SELECT * FROM teams ORDER BY team_name ASC;";
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

    //Request to join team
    public boolean requestTeamJoin(Principal principal, int teamId){
        String sql1 = "SELECT user_id FROM users WHERE username =?;";
        String sql2 = "INSERT INTO team_users (user_id, team_id, accepted) VALUES (?,?,false);";
        int userId = 0;

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql1, principal.getName());
            if (rowSet.next()) {
                userId = rowSet.getInt("user_id");
            }
            jdbcTemplate.update(sql2, userId, teamId);
            return true;
        }catch(DataIntegrityViolationException e){
            return false;
        }catch(CannotGetJdbcConnectionException e){
            return false;
        }
    }

    public List<TeamDto> teamsIAmCaptainOn(Principal principal){
        List<TeamDto> teams = new ArrayList<>();
        String sql = "SELECT captain_id, team_name, team_id FROM teams " +
                "JOIN users ON captain_id = user_id " +
                "WHERE username = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, principal.getName());
        while(rowSet.next()){
            teams.add(mapRowSetToTeamDto(rowSet));
        }
        return teams;
    }


    private TeamDto mapRowSetToTeamDto(SqlRowSet rowSet) {
        TeamDto teamDto = new TeamDto();
        //Todo: Add logic to fill teamDto
        teamDto.setCaptainId(rowSet.getInt("captain_id"));
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
        team.setAccepting(rowSet.getBoolean("isaccepting"));
        team.setMaxPlayers(rowSet.getInt("max_players"));
        return team;
    }

}

