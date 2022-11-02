package com.example.opengymapp;

import android.os.Parcel;
import android.os.Parcelable;

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
    public static final Parcelable.Creator<Gym> CREATOR = new
            Parcelable.Creator<Gym>() {

                @Override
                public Gym createFromParcel(Parcel parcel) {
                    return new Gym(parcel);
                }

                @Override
                public Gym[] newArray(int size) {
                    return new Gym[0];
                }
            };

    public Gym(String gymName, int numCourts, String address) {
        this.gymName = gymName;
        this.numCourts = numCourts;
        this.address = address;
        this.playerName = playerName;
        this.hour = hour;
        this.docID = "No docID yet";
    }
    public Gym(Parcel parcel) {
        gymName = parcel.readString();
        numCourts = parcel.readInt();
        address = parcel.readString();
        playerName = parcel.readString();
        hour = parcel.readString();
        docID = parcel.readString();
    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gymName);
        dest.writeInt(numCourts);
        dest.writeString(address);
        dest.writeString(playerName);
        dest.writeString(hour);
        dest.writeString(docID);
    }


    // A default constructor is required for the Parcelable interface to work
    public Gym() {
        gymName = "No name";
        numCourts = 0;
        address = "No address";
        playerName = "";
        hour = "";
        this.docID = "No docID yet";
    }
    @Override
    public int describeContents() {
        return 0;
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
