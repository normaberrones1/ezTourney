package com.techelevator.model;

public class TourneyTeamDto {
    private int teamId;

    private String teamName;
    private  boolean accepted;
    private boolean eliminated;
    private int roundEliminated;

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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public void setEliminated(boolean eliminated) {
       this.eliminated = eliminated;
    }

    public int getRoundEliminated() {
        return roundEliminated;
    }

    public void setRoundEliminated(int roundEliminated) {
        this.roundEliminated = roundEliminated;
    }
}
