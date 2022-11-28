package com.example.opengymapp;

import android.os.Parcel;
import android.os.Parcelable;

public class GymReservation implements Parcelable{
    private String playerName;
    private String date;
    private Gym gym;
    private String time;
    private String docID;

    public GymReservation(String playerName, String date, Gym gym, String time, String docID) {
        this.playerName = playerName;
        this.date = date;
        this.gym = gym;
        this.time = time;
        this.docID = docID;
    }

    public GymReservation(String playerName, String date, Gym gym, String time){
        this.playerName = playerName;
        this.date = date;
        this.gym = gym;
        this.time = time;
        docID = "No docID yet";
    }

    public GymReservation(){
        playerName = "";
        date = "";
        gym = new Gym();
        time = "";
        docID = "No docID yet";
    }

    public GymReservation(Parcel parcel) {
        playerName = parcel.readString();
        date = parcel.readString();
        // need to fix this
//        gym = parcel.readTypedObject(Creator<Gym>);
        time = parcel.readString();
        docID = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(playerName);
        dest.writeString(date);
        // need to add write method for gym object
        dest.writeTypedObject();
        dest.writeString(time);
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
