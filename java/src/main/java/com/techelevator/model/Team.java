package com.techelevator.model;

public class Team {
    private int teamId;
    private String teamName;
    private int captainId;

    private int gameId;
    private boolean  isAccepting;

    private int maxPlayers;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }


    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getCaptainId() {
        return captainId;
    }

    public void setCaptain_id(int captainId) {
        this.captainId = captainId;
    }

    public boolean getIsAccepting() {
        return isAccepting;
    }

    public void setIsAccepting(boolean isAccepting) {
        this.isAccepting = isAccepting;
    }
}
