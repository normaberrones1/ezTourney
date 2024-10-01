package com.techelevator.model;

public class Team {
    private int teamId;
    private String teamName;
    private int captainId;
    private int gameId;
    private boolean  isAccepting;
    private int maxPlayers;
    public Team(){

    }

    public Team(int teamId,String teamName, int captainId, int gameId, boolean isAccepting,int maxPlayers){
        this.teamId = teamId;
        this.teamName = teamName;
        this.captainId = captainId;
        this.gameId = gameId;
        this.isAccepting = isAccepting;
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

    public void setCaptainId(int captainId) {
        this.captainId = captainId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean isAccepting() {
        return isAccepting;
    }

    public void setAccepting(boolean accepting) {
        isAccepting = accepting;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
