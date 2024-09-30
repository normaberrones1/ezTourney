package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;
import com.techelevator.model.UserDto;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMemberDao implements MemberDao{

    private final JdbcTemplate template;

    JdbcMemberDao(JdbcTemplate template){
        this.template = template;
    }

    public UserDto getMemberById(int userId){
        String sql = "SELECT user_id, username FROM users WHERE user_id = ?";

        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, userId);
            if (rowSet.next()) {
                return mapRowToUserDto(rowSet);
            }
        }catch(CannotGetJdbcConnectionException e){

        }

        return null;
    }

    public List<UserDto> getMembersByTeamId(int teamId){
        List<UserDto> users = new ArrayList<>();

        String sql = "SELECT users.user_id AS user_id, users.username FROM users " +
                "JOIN team_users ON users.user_id = team_users " +
                "WHERE team_users.team_id = ? ";
        SqlRowSet rowSet = template.queryForRowSet(sql, teamId);

        while(rowSet.next()){
            users.add(mapRowToUserDto(rowSet));
        }

        return users;
    }

    public List<TeamDto> getMyTeams(Principal principal){
        List<TeamDto> teams = new ArrayList<>();

        String sql = "SELECT team_name, captain_id FROM teams " +
                "JOIN team_users ON team.team_id, team_users.team_id " +
                "JOIN user ON user.user_id team_users.user_id " +
                "WHERE user.username = ?";

        SqlRowSet rowSet = template.queryForRowSet(sql, principal.getName());

        while(rowSet.next()){
            teams.add(mapRowToTeamDto(rowSet));
        }

        return teams;
    }

    private UserDto mapRowToUserDto(SqlRowSet rowSet){
        UserDto user = new UserDto();
        user.setUserId(rowSet.getInt("user_id"));
        user.setUsername(rowSet.getString("username"));
        return user;
    }

    private TeamDto mapRowToTeamDto(SqlRowSet rowSet){
        TeamDto team = new TeamDto();
        team.setCaptainId(rowSet.getInt("captain_id"));
        team.setTeamName(rowSet.getString("team_name"));
        return team;
    }
}
