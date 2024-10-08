package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;

public class Tournament {
    private int tourneyId;
    private String tourneyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private int entry_fee;
    private String prizeDesc;
    private String tourneyDesc;
    private int gameId;
    private int winner;
    private int round;
    private String gameName;
    private String winningTeamName;

    private boolean isPrivate;

    private boolean singlesEvent;

    private int winningUserId;

    private String winningUserName;


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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getWinningTeamName() {
        return winningTeamName;
    }

    public void setWinningTeamName(String winningTeamName) {
        this.winningTeamName = winningTeamName;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public boolean isSinglesEvent() {
        return singlesEvent;
    }

    public void setSinglesEvent(boolean singlesEvent) {
        this.singlesEvent = singlesEvent;
    }

    public int getWinningUserId() {
        return winningUserId;
    }

    public void setWinningUserId(int winningUserId) {
        this.winningUserId = winningUserId;
    }

    public String getWinningUserName() {
        return winningUserName;
    }

    public void setWinningUserName(String winningUserName) {
        this.winningUserName = winningUserName;
    }
}
