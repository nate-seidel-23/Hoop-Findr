package com.example.opengymapp;

public class Gym {
    private String gymName;
    private int numCourts;
    private String address, playerName, hour, docID;

    public Gym(String gymName, int numCourts, String address, String playerName, String hour, String docID) {
        this.gymName = gymName;
        this.numCourts = numCourts;
        this.address = address;
        this.playerName = playerName;
        this.hour = hour;
        this.docID = docID;
    }

    public Gym(String gymName, int numCourts, String address) {
        this.gymName = gymName;
        this.numCourts = numCourts;
        this.address = address;
        this.playerName = playerName;
        this.hour = hour;
        this.docID = "No docID yet";
    }


    // A default constructor is required for the Parcelable interface to work
    public Gym() {
        gymName = "No name";
        numCourts = 0;
        address = "No address";
        playerName = ";
        hour = "";
        this.docID = "No docID yet";
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public int getNumCourts() {
        return numCourts;
    }

    public void setNumCourts(int numCourts) {
        this.numCourts = numCourts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
