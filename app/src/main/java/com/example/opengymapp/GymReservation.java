package com.example.opengymapp;

public class GymReservation {
    private String gymName;
    private int numCourts;
    private String address;

    public GymReservation(String gymName, int numCourts, String address) {
        this.gymName = gymName;
        this.numCourts = numCourts;
        this.address = address;
    }

    GymReservation ymca = new GymReservation("YMCA", 3,
            "1400 W Northwest Hwy, Palatine, IL 60067");

    GymReservation community = new GymReservation("Palatine Community Center", 2,
            "1250 E Wood St, Palatine, IL 60067");

    GymReservation birchwood = new GymReservation("Birchwood recreation center", 1,
            "435 W. Illinois Avenue");

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
}
