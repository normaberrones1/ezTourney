package com.techelevator.model;

public class BracketDto {
    private String teamName;
    private int score;

    //This is displayed as a round-#-seat-#
    private String matchId;

    private int winner;
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public int getIsWon() {
        return winner;
    }

    public void setIsWon(int isWon) {
        this.winner = isWon;
    }
}
