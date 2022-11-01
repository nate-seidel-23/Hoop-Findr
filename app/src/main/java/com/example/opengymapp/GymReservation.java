package com.example.opengymapp;

public class GymReservation {
    private String playerName;
    private String time;

    public GymReservation(String playerName, String time) {
        this.playerName = playerName;
        this.time = time;
    }



    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
