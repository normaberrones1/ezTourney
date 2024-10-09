package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcTournamentDao implements TournamentDao {

    private JdbcTemplate template;

    @Autowired
    private MemberDao memberDao;

    private String dtoSelect = "SELECT tourney_id, tourney_name, entry_fee, tourney_desc, " +
            "start_date, end_date";

    JdbcTournamentDao(JdbcTemplate template, MemberDao memberDao) {
        this.template = template;
        this.memberDao = memberDao;
    }

    // PAST AND PRESENT
    public List<TournamentDto> getAllTournamentHistory() {
        List<TournamentDto> tournaments = new ArrayList<>();

        String sql = dtoSelect + " FROM TOURNAMENT WHERE is_private = false;";



        try {
            SqlRowSet rowSet = template.queryForRowSet(sql);
            while (rowSet.next()) {
                TournamentDto dto = mapRowToTournamentDto(rowSet);
                dto.setDirectorNames(getUserNamesInTournament(rowSet.getInt("tourney_id")));
                tournaments.add(dto);
            }

        } catch (CannotGetJdbcConnectionException e) {
        }
        return tournaments;
    }

    public List<TournamentDto> getAllActiveTournaments() {
        List<TournamentDto> tournaments = new ArrayList<>();

        String sql = dtoSelect + " FROM TOURNAMENT WHERE is_private = false AND current_timestamp < start_date;";

        try {
            SqlRowSet rowSet = template.queryForRowSet(sql);
            while (rowSet.next()) {
                TournamentDto dto = mapRowToTournamentDto(rowSet);
                dto.setDirectorNames(getUserNamesInTournament(rowSet.getInt("tourney_id")));
                tournaments.add(dto);
            }
        } catch (CannotGetJdbcConnectionException e) {
        }
        return tournaments;
    }

    @Override
    public List<TournamentDto> getTournamentsByFilters(String status, Date startDate, Date endDate) {
        List<TournamentDto> tournaments = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM tournament WHERE is_private = false");

        if(status != null && !status.equalsIgnoreCase("All")) {
            if(status.equalsIgnoreCase("Current")) {
                sql.append(" AND start_date <= CURRENT_DATE AND end_date >= CURRENT_DATE");
            } else if (status.equalsIgnoreCase("Upcoming")) {
                sql.append(" AND start_date > CURRENT_DATE");
            } else if (status.equalsIgnoreCase("Past")) {
                sql.append(" AND end_date < CURRENT_DATE");
            }
        }
        sql.append(" ORDER BY start_date DESC;");

        SqlRowSet rowSet = template.queryForRowSet(sql.toString());

        while (rowSet.next()) {
            TournamentDto dto = mapRowToTournamentDto(rowSet);
            tournaments.add(dto);
        }


        return tournaments;
    }

    @Override
    public List<TournamentDto> getTournamentsForDirectors(int directorId, String status, Date startDate, Date endDate) {
        List<TournamentDto> tournaments = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM tournament JOIN tourney_directors ON tournament.tournament_id = tourney_directors.tourney_id WHERE tourney_directors = ?");

        if(status != null && !status.equalsIgnoreCase("All")) {
            if(status.equalsIgnoreCase("Current")) {
                sql.append(" AND start_date <= CURRENT_DATE AND end_date >= CURRENT_DATE");
            } else if (status.equalsIgnoreCase("Upcoming")) {
                sql.append(" AND start_date > CURRENT_DATE");
            } else if (status.equalsIgnoreCase("Past")) {
                sql.append(" AND end_date < CURRENT_DATE");
            }
        }
        sql.append(" ORDER BY start_date DESC;");

        SqlRowSet rowSet = template.queryForRowSet(sql.toString());

        while (rowSet.next()) {
            TournamentDto dto = mapRowToTournamentDto(rowSet);
            tournaments.add(dto);
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
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return null;
    }

    public boolean acceptTourneyTeam(int teamId, int tourneyId) {
        String sql = "UPDATE team_tourney SET isaccepted = true WHERE team_id = ? AND tourney_id = ?;";
        try {
            int numOfRows = template.update(sql, teamId, tourneyId);
            if (numOfRows > 0) {
                return true;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return false;

    }

    public Tournament createTournament(Tournament newTournament, Principal principal) {
        int newTournamentId = 0;

        String sql = "INSERT INTO tournament(tourney_name, start_date, end_date, location, entry_fee," +
                " prize_desc, tourney_desc, game_id, round, is_private, is_singles_event) " +
                "VALUES (?,?,?,?,?,?,?,?,1,?,?) RETURNING tourney_id;";
        String sql2 = "INSERT INTO tourney_directors (tourney_id, director_id) VALUES (?,?);";
        try {
            newTournamentId = template.queryForObject(sql, Integer.class, newTournament.getTourneyName(),
                    newTournament.getStartDate(), newTournament.getEndDate(),
                    newTournament.getLocation(), newTournament.getEntry_fee(), newTournament.getPrizeDesc(),
                    newTournament.getTourneyDesc(), newTournament.getGameId(), newTournament.isPrivate(),
                    newTournament.isSinglesEvent());
            template.update(sql2, newTournamentId, memberDao.getUserIdByName(principal.getName()));
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getTournamentById(newTournamentId);
    }

    public Tournament updateTournament(Tournament tournament) {
        String sql = "UPDATE tournament SET " +
                " tourney_name = ?, start_date = ? , end_date = ?, location = ?, entry_fee = ?, prize_desc = ?, " +
                "tourney_desc= ? , round = ?, winner_id = ?, winning_user_id = ?, is_private = ? , is_singles_event = ?," +
                " game_id=? WHERE tourney_id = ?";
        try {
            int numOfRows = template.update(sql, tournament.getTourneyName(), tournament.getStartDate(),
                    tournament.getEndDate(), tournament.getLocation(), tournament.getEntry_fee(),
                    tournament.getPrizeDesc(),
                    tournament.getTourneyDesc(),
                    tournament.getRound(), tournament.getWinner(),tournament.getWinningUserId(), tournament.isPrivate(),
                    tournament.isSinglesEvent(), tournament.getGameId(),tournament.getTourneyId());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return tournament;
    }

    public void nextRound(int tourneyId) {

    }

    @Override
    public WinLossDto getTourneyWinsAndLosses(int teamId) {
        WinLossDto winLoss = new WinLossDto();
        String winnerSql = "SELECT COUNT(*) as wins FROM tournament " +
                "where winner_id = ?;";
        String loserSql = "SELECT COUNT(*) as losses FROM tournament " +
                "JOIN team_tourney ON tournament.tourney_id = team_tourney.tourney_id " +
                "WHERE winner_id NOTNULL AND winner_id != ? AND team_id = ?;";

        try {
            SqlRowSet winsRows = template.queryForRowSet(winnerSql, teamId);
            if (winsRows.next()) {
                winLoss.setWins(winsRows.getInt("wins"));
            }
            SqlRowSet lossRows = template.queryForRowSet(loserSql, teamId, teamId);
            if (lossRows.next()) {
                winLoss.setLosses(lossRows.getInt("losses"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
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

            while (rowSet.next()) {
                currentTournaments.add(mapRowToTournamentDto(rowSet));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return currentTournaments;
    }

    public WinLossDto getMatchWinLoss(int teamId) {
        WinLossDto winLoss = new WinLossDto();
        String winSql = "SELECT COUNT(*) as wins FROM tourney_matches " +
                "WHERE set_winner = ?";
        String lossSql = "SELECT COUNT(*) as loss FROM tourney_matches " +
                "WHERE set_winner NOTNULL AND set_winner != ? " +
                "AND (team_1_id = ? OR team_2_id = ?) ";

        SqlRowSet wins = template.queryForRowSet(winSql, teamId);
        if (wins.next()) {
            winLoss.setWins(wins.getInt("wins"));
        }
        SqlRowSet losses = template.queryForRowSet(lossSql, teamId, teamId, teamId);
        if (losses.next()) {
            winLoss.setLosses(losses.getInt("loss"));
        }

        return winLoss;
    }

    public boolean teamRequestToJoinTourney(int tourneyId, int teamId){
        String sql = "INSERT INTO team_tourney(team_id, tourney_id, isAccepted, eliminated) " +
                "VALUES (?,?, false, false)";
        try {
            template.update(sql, teamId, tourneyId);
            return true;
        }catch(CannotGetJdbcConnectionException e){
            return false;
        }catch(DataIntegrityViolationException e){
            return false;
        }
    }

    public boolean userRequestToJoinTourney(int tourneyId, Principal principal){
        String sql = "INSERT INTO tourney_user(user_id, tourney_id, isAccepted, eliminated) " +
                "VALUES (?,?, 'false', 'false')";
        try {
            template.update(sql, memberDao.getUserIdByName(principal.getName()), tourneyId);
            return true;
        }catch(CannotGetJdbcConnectionException e){
            return false;
        }catch(DataIntegrityViolationException e){
            return false;
        }
    }

    public boolean isUserDirector(Principal principal, int tourneyId){
        return getUserNamesInTournament(tourneyId).contains(principal.getName());
    }

    private List<String> getUserNamesInTournament(int tourneyId) {
        List<String> usernames = new ArrayList<>();
        String sql = "SELECT username FROM users " +
                "JOIN tourney_directors ON user_id = director_id " +
                "WHERE tourney_id = ?";
        SqlRowSet rowSet = template.queryForRowSet(sql, tourneyId);
        while (rowSet.next()) {
            usernames.add(rowSet.getString("username"));
        }
        return usernames;
    }

    public List<TourneyTeamDto> getTourneyTeams(int tourneyId) {
        List<TourneyTeamDto> tourneyTeams = new ArrayList<>();
        String sql = "SELECT team_tourney.team_id, team_name, isaccepted, eliminated," +
                "round_eliminated FROM team_tourney " +
                "JOIN teams ON teams.team_id = team_tourney.team_id " +
                "WHERE tourney_id = ?;";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, tourneyId);
            while (rowSet.next()) {
                TourneyTeamDto dto = mapRowToTourneyTeamDto(rowSet);
                tourneyTeams.add(dto);
            }
        } catch (CannotGetJdbcConnectionException e) {
        }
        return tourneyTeams;
    }
    
    public Tournament getTourneyDetailsViewById(int tourneyId){
        String sql = "SELECT tr.tourney_id,tr.tourney_name,tr.start_date,tr.end_date,tr.location,tr.entry_fee,tr.prize_desc," +
                "tr.tourney_desc,tr.game_id, tr.winner_id,tr.is_singles_event, tr.winning_user_id, u.username, tr.round," +
                " tr.is_private, g.game_name,t.team_name FROM tournament tr JOIN games g ON " +
                "tr.game_id = g.game_id " +
                "LEFT JOIN teams t ON tr.winner_id = t.team_id " +
                "LEFT JOIN users u ON tr.winning_user_id = u.user_id " +
                "WHERE tr.tourney_id=?;";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, tourneyId);
            if (rowSet.next()) {
                return mapRowToTournamentView(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return null;
    }

    public boolean acceptTourneyUser(int userId, int tourneyId) {
        String sql = "UPDATE tourney_user SET isaccepted = true WHERE user_id = ? AND tourney_id = ?;";
        try {
            int numOfRows = template.update(sql, userId, tourneyId);
            if (numOfRows > 0) {
                return true;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return false;

    }

    public List<TourneyUserDto> getTourneyUsers(int tourneyId) {
        List<TourneyUserDto> tourneyUsers = new ArrayList<>();
        String sql = "SELECT tourney_user.user_id, username, isaccepted, eliminated," +
                "round_eliminated FROM tourney_user " +
                "JOIN users ON users.user_id = tourney_user.user_id " +
                "WHERE tourney_id = ?; ";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, tourneyId);
            while (rowSet.next()) {
                TourneyUserDto dto = mapRowToTourneyUserDto(rowSet);
                tourneyUsers.add(dto);
            }
        } catch (CannotGetJdbcConnectionException e) {
        }
        return tourneyUsers;
    }

    private TourneyUserDto mapRowToTourneyUserDto(SqlRowSet rowSet) {
        TourneyUserDto tourneyUserDto = new TourneyUserDto();
        tourneyUserDto.setUserId(rowSet.getInt("user_id"));
        tourneyUserDto.setUserName(rowSet.getString("username"));
        tourneyUserDto.setAccepted(rowSet.getBoolean("isaccepted"));
        tourneyUserDto.setEliminated(rowSet.getBoolean("eliminated"));
        tourneyUserDto.setRoundEliminated(rowSet.getInt("round_eliminated"));
        return tourneyUserDto;
    }

    private TourneyTeamDto mapRowToTourneyTeamDto(SqlRowSet rowSet) {
        TourneyTeamDto tourneyteamDto = new TourneyTeamDto();
        tourneyteamDto.setTeamId(rowSet.getInt("team_id"));
        tourneyteamDto.setTeamName(rowSet.getString("team_name"));
        tourneyteamDto.setAccepted(rowSet.getBoolean("isaccepted"));
        tourneyteamDto.setEliminated(rowSet.getBoolean("eliminated"));
        tourneyteamDto.setRoundEliminated(rowSet.getInt("round_eliminated"));
        return tourneyteamDto;
    }

    private TournamentDto mapRowToTournamentDto(SqlRowSet rowSet) {
        TournamentDto dto = new TournamentDto();
        dto.setTourneyId(rowSet.getInt("tourney_id"));
        dto.setTourneyName(rowSet.getString("tourney_name"));
        dto.setEntryFee(BigDecimal.valueOf(rowSet.getInt("entry_fee")));
        dto.setTourneyDesc(rowSet.getString("tourney_desc"));
        dto.setStartDate(rowSet.getDate("start_date"));
        dto.setEndDate(rowSet.getDate("end_date"));
        return dto;
    }

    private Tournament mapRowToTournament(SqlRowSet rowSet) {
        Tournament tournament = new Tournament();
        tournament.setTourneyId(rowSet.getInt("tourney_id"));
        tournament.setTourneyName(rowSet.getString("tourney_name"));
        tournament.setStartDate(rowSet.getDate("start_Date").toLocalDate());
        tournament.setEndDate(rowSet.getDate("end_date").toLocalDate());
        tournament.setLocation(rowSet.getString("location"));
        tournament.setEntry_fee(rowSet.getInt("entry_fee"));
        tournament.setPrizeDesc(rowSet.getString("prize_desc"));
        tournament.setGameId(rowSet.getInt("game_id"));
        tournament.setTourneyDesc(rowSet.getString("tourney_desc"));
        tournament.setRound(rowSet.getInt("round"));
        tournament.setWinner(rowSet.getInt("winner_id"));
        tournament.setPrivate(rowSet.getBoolean("is_private"));
        tournament.setSinglesEvent(rowSet.getBoolean("is_singles_event"));
        tournament.setWinningUserId(rowSet.getInt("winning_user_id"));

        return tournament;
    }
    private Tournament mapRowToTournamentView(SqlRowSet rowSet) {
        Tournament tournament = new Tournament();
        tournament.setTourneyId(rowSet.getInt("tourney_id"));
        tournament.setTourneyName(rowSet.getString("tourney_name"));
        tournament.setStartDate(rowSet.getDate("start_Date").toLocalDate());
        tournament.setEndDate(rowSet.getDate("end_date").toLocalDate());
        tournament.setLocation(rowSet.getString("location"));
        tournament.setEntry_fee(rowSet.getInt("entry_fee"));
        tournament.setPrizeDesc(rowSet.getString("prize_desc"));
        tournament.setGameId(rowSet.getInt("game_id"));
        tournament.setTourneyDesc(rowSet.getString("tourney_desc"));
        tournament.setRound(rowSet.getInt("round"));
        tournament.setWinner(rowSet.getInt("winner_id"));
        tournament.setGameName(rowSet.getString("game_name"));
        tournament.setWinningTeamName(rowSet.getString("team_name"));
        tournament.setPrivate(rowSet.getBoolean("is_private"));
        tournament.setSinglesEvent(rowSet.getBoolean("is_singles_event"));
        tournament.setWinningUserId(rowSet.getInt("winning_user_id"));
        tournament.setWinningUserName(rowSet.getString("username"));

        return tournament;
    }
}
