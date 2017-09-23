package com.example.prad.scouter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Prad on 5/28/2017.
 */

public class Scout implements Parcelable {
    String team;
    String driveTrain;
    String lift;
    String intakeOuttake;
    String extraNotes;

    public Scout() {
        team = "";
        driveTrain = "";
        lift = "";
        intakeOuttake = "";
        extraNotes = "";
    }

    public Scout(String[] array) {
         if (array.length == 5) {
             team = array[0];
             driveTrain = array[1];
             lift = array[2];
             intakeOuttake = array[3];
             extraNotes = array[4];
         }
         else {
             team = "";
             driveTrain = "";
             lift = "";
             intakeOuttake = "";
             extraNotes = "";
         }
    }

    private Scout(Parcel in) {
        String[] array = in.createStringArray();
        if (array.length == 5) {
            team = array[0];
            driveTrain = array[1];
            lift = array[2];
            intakeOuttake = array[3];
            extraNotes = array[4];
        }
        else {
            team = "";
            driveTrain = "";
            lift = "";
            intakeOuttake = "";
            extraNotes = "";
        }
    }

    public String[] getScoutData() {
        return new String[]{team, driveTrain, lift, intakeOuttake, extraNotes};
    }

    public String toString() {
        return "Tm: "+team+", Dr: "+driveTrain+", Lf: "+lift+", I/O:"+intakeOuttake;
    }

    public String altToString() {
        if (!(extraNotes.equals(""))) {
            return team + "_" + driveTrain + "_" + lift + "_" + intakeOuttake + "_" + extraNotes;
        }
        return team + "_" + driveTrain + "_" + lift + "_" + intakeOuttake+"_"+"|";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{team, driveTrain, lift, intakeOuttake, extraNotes});
    }

    public static final Parcelable.Creator<Scout> CREATOR = new Parcelable.Creator<Scout>() {
        public Scout createFromParcel(Parcel in) {
            return new Scout(in);
        }

        public Scout[] newArray(int size) {
            return new Scout[size];
        }
    };
}