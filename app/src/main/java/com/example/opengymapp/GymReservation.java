package com.example.opengymapp;

import android.os.Parcel;
import android.os.Parcelable;


public class GymReservation implements Parcelable{
    private String playerName;
    private String date;
    private String gym;
    private String time;
    private String court;
    private String docID;

    public GymReservation(String playerName, String date, String gym, String time, String court,
                          String docID) {
        this.playerName = playerName;
        this.date = date;
        this.gym = gym;
        this.time = time;
        this.court = court;
        this.docID = docID;
    }

    public GymReservation(String playerName, String date, String gym, String time, String court){
        this.playerName = playerName;
        this.date = date;
        this.gym = gym;
        this.time = time;
        this.court = court;
        docID = "No docID yet";
    }

    public GymReservation(){
        playerName = "";
        date = "";
        gym = "";
        time = "";
        court = "";
        docID = "No docID yet";
    }

    public GymReservation(Parcel parcel) {
        playerName = parcel.readString();
        date = parcel.readString();
        gym = parcel.readString();
        time = parcel.readString();
        court = parcel.readString();
        docID = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(playerName);
        dest.writeString(date);
        dest.writeString(gym);
        dest.writeString(time);
        dest.writeString(court);
        dest.writeString(docID);
    }

    public static final Parcelable.Creator<GymReservation> CREATOR = new
            Parcelable.Creator<GymReservation>() {

                @Override
                public GymReservation createFromParcel(Parcel parcel) {
                    return new GymReservation(parcel);
                }

                @Override
                public GymReservation[] newArray(int size) {
                    return new GymReservation[0];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "Name: " + playerName + " " + date + " " + gym;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }
}
