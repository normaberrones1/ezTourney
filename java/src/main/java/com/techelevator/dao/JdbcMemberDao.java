package com.techelevator.dao;

import com.techelevator.model.*;
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
                "JOIN team_users ON users.user_id = team_users.user_id " +
                "WHERE team_users.team_id = ?  AND " +
                "accepted = true;";
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

    public boolean isCurrentUserTeamCaptain(Principal principal, int teamId){
        String sql = "SELECT COUNT(*) FROM teams " +
                "JOIN users ON captain_id = user_id " +
                "WHERE team_id = ? AND username = ?";

        SqlRowSet rowSet = template.queryForRowSet(sql, teamId, principal.getName());

        if(rowSet.next()){
            if(rowSet.getInt("count") >= 1){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getUserIdByName(String username) {
        String sql = "SELECT user_id FROM users WHERE username = ?";
        SqlRowSet rowSet = template.queryForRowSet(sql,username);
        if(rowSet.next()){
            return rowSet.getInt("user_id");
        }
        return 0;
    }

    public List<UserDto> getPendingJoinRequests(int teamId){
        List<UserDto> users = new ArrayList<>();
        String sql = "SELECT username, users.user_id FROM users " +
                "JOIN team_users ON team_users.user_id = users.user_id " +
                "WHERE team_id = ?";
        SqlRowSet rowSet = template.queryForRowSet(sql, teamId);
        while(rowSet.next()){
            users.add(mapRowToUserDto(rowSet));
        }
        return users;
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
