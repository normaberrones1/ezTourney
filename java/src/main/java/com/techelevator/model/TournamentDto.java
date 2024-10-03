package com.techelevator.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TournamentDto {
    private int tourneyId;
    private String tourneyName;
    private BigDecimal entryFee;
    private String tourneyDesc;
    private Date startDate;
    private List<String> directorNames;

    public int getTourneyId() {
        return tourneyId;
    }

    public void setTourneyId(int tourneyId) {
        this.tourneyId = tourneyId;
    }

    public String getTourneyName() {
        return tourneyName;
    }

    public void setTourneyName(String tourneyName) {
        this.tourneyName = tourneyName;
    }

    public BigDecimal getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(BigDecimal entryFee) {
        this.entryFee = entryFee;
    }

    public String getTourneyDesc() {
        return tourneyDesc;
    }

    public void setTourneyDesc(String tourneyDesc) {
        this.tourneyDesc = tourneyDesc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<String> getDirectorNames() {
        return directorNames;
    }

    public void setDirectorNames(List<String> directorNames) {
        this.directorNames = directorNames;
    }
}
