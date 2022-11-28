package com.example.opengymapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Gym implements Parcelable {
    private String gymName;
    private int numCourts;
    private String address, docID;

    public Gym(String gymName, int numCourts, String address, String docID) {
        this.gymName = gymName;
        this.numCourts = numCourts;
        this.address = address;
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
        this.docID = "No docID yet";
    }

    public Gym(Parcel parcel) {
        gymName = parcel.readString();
        numCourts = parcel.readInt();
        address = parcel.readString();
        docID = parcel.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gymName);
        dest.writeInt(numCourts);
        dest.writeString(address);
        dest.writeString(docID);
    }


    // A default constructor is required for the Parcelable interface to work
    public Gym() {
        gymName = "No name";
        numCourts = 0;
        address = "No address";
        this.docID = "No docID yet";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Gym[] gyms = {
            new Gym("YMCA", 3, "1400 W Northwest Hwy, Palatine, IL 60067"),
            new Gym("Birchwood", 1, "435 W Illinois Ave, Palatine, IL 60067"),
            new Gym("Palatine Community Center", 2,"250 E Wood St, Palatine, IL 60067")
    };

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

}

