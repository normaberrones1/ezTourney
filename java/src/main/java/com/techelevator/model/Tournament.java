package com.techelevator.model;

import java.util.Date;

public class Tournament {
    private int tourneyId;
    private String tourneyName;
    private Date startDate;
    private Date endDate;
    private String location;
    private int entry_fee;
    private String prizeDesc;
    private String tourneyDesc;
    private int gameId;
    private int directorId;
    private int winner;
     private int round;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEntry_fee() {
        return entry_fee;
    }

    public void setEntry_fee(int entry_fee) {
        this.entry_fee = entry_fee;
    }

    public String getPrizeDesc() {
        return prizeDesc;
    }

    public void setPrizeDesc(String prizeDesc) {
        this.prizeDesc = prizeDesc;
    }

    public String getTourneyDesc() {
        return tourneyDesc;
    }

    public void setTourneyDesc(String tourneyDesc) {
        this.tourneyDesc = tourneyDesc;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
