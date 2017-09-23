package com.example.prad.scouter;

/**
 * Created by Prad on 5/28/2017.
 */

public class Scout {
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
}