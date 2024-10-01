package com.techelevator.dao;


import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentDto;
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

    public List<TournamentDto> getAllTournaments(){
        List<TournamentDto> tournaments = new ArrayList<>();
        String sql = dtoSelect + " FROM TOURNAMENT JOIN users ON director_id = user_id";

        SqlRowSet rowSet = template.queryForRowSet(sql);
        while(rowSet.next()){
            tournaments.add(mapRowToTournamentDto(rowSet));
        }

        return tournaments;
    }

    public Tournament getTournamentById(int id){
        return null;
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
}
