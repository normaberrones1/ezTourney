package com.techelevator.dao;

import com.techelevator.model.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTeamDaoTests extends BaseDaoTests {
    protected static final Team TEAM_1 = new Team(1, "League of Cornholers", 1, 1, true, 10);
    protected static final Team TEAM_2 = new Team(2, "Champs of the Underground", 2, 2, true, 10);
    protected static final Team TEAM_3 = new Team(3, "Freaked Out", 3, 3, true, 10);

    private JdbcTeamDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTeamDao(jdbcTemplate);
    }

    @Test
    public void getTeamById_given_valid_teamId_returns_team() {
        Team actualTeam = sut.getTeamById(TEAM_1.getTeamId());
        assertEquals(TEAM_1, actualTeam);
    }

    @Test
    public void getTeamByTeamName_valid_teamName_returns_teamName() {
        Team actualTeam = sut.getTeamByTeamName(TEAM_2.getTeamName());
        assertEquals(TEAM_2, actualTeam);
    }

    @Test
    public void getTeamById_given_invalid_teamId_returns_null() {
        Team actualTeam = sut.getTeamById(-1);
        Assert.assertNull(actualTeam);
    }

    @Test
    public void getTeams_returns_all_teams() {
        List<Team> teams = sut.getTeamNames();
        Assert.assertNotNull(teams);
        Assert.assertTrue(teams.size() >= 3);
        assertEquals(TEAM_1, teams.get(0));
        assertEquals(TEAM_2, teams.get(1));
        assertEquals(TEAM_3, teams.get(2));
    }

    @Test
    public void createTeam_creates_a_team() {
        Team newTeam = new Team();
        newTeam.setTeamName("Dream Catchers");
        newTeam.setCaptainId(3);
        newTeam.setGameId(3);
        newTeam.setAccepting(true);
        newTeam.setMaxPlayers(10);

        newTeam = sut.createTeam(newTeam);

        Team retrievedTeam = sut.getTeamByTeamName(newTeam.getTeamName());
        assertEquals(newTeam, retrievedTeam);
    }

    @Test
    public void updateTeam_updates_a_team() {
        //Creating a team for test
        Team inputTeam = new Team(0,"newteam", 1, 2,true,10);
        inputTeam = sut.createTeam(inputTeam);

        //Modifying few fields
        inputTeam.setTeamName("updatedTeam");
        inputTeam.setMaxPlayers(12);
        inputTeam.setAccepting(false);

        //Updating team
        Team updatedTeam = sut.updateTeam(inputTeam);
        //Verifying whether updatedTeam is same as inout team

        assertEquals(inputTeam, updatedTeam);
    }


    private void assertEquals(Team expectedTeam, Team actualTeam) {
        Assert.assertEquals("TeamId did not match", expectedTeam.getTeamId(), actualTeam.getTeamId());
        Assert.assertEquals("TeamName did not match", expectedTeam.getTeamName(), actualTeam.getTeamName());
        Assert.assertEquals("CaptainId did not match", expectedTeam.getCaptainId(), actualTeam.getCaptainId());
        Assert.assertEquals("GameId did not match", expectedTeam.getGameId(), actualTeam.getGameId());
        Assert.assertEquals("isAccepting did not match", expectedTeam.isAccepting(), actualTeam.isAccepting());
        Assert.assertEquals("MaxPlayers did not match", expectedTeam.getMaxPlayers(), actualTeam.getMaxPlayers());
    }
}
