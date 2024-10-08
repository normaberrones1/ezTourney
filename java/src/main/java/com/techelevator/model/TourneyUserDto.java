package com.techelevator.model;

public class TourneyUserDto {
    private int userId;

    private String userName;
    private  boolean accepted;
    private boolean eliminated;
    private int roundEliminated;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
