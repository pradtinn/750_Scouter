package com.example.prad.scouter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewScoutActivity extends AppCompatActivity {

    Toolbar goBackToolbar;
    Scout scout;
    TextView teamView;
    TextView drivetrainView;
    TextView liftView;
    TextView intakeOuttakeView;
    TextView extraNotesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scout);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Lato-Medium.ttf");

        goBackToolbar = (Toolbar)findViewById(R.id.go_back_toolbar2_id);
        goBackToolbar.setTitle("View Team");
        TextView tv = (TextView)goBackToolbar.getChildAt(0);
        tv.setTypeface(tf);
        setSupportActionBar(goBackToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent receivedIntent = getIntent();
        scout = new Scout(receivedIntent.getBundleExtra("DATA_BUNDLE").getStringArray("SCOUT_INFO"));

        teamView = (TextView)findViewById(R.id.team_view_id);
        drivetrainView = (TextView)findViewById(R.id.drivetrain_view_id);
        liftView = (TextView)findViewById(R.id.lift_view_id);
        intakeOuttakeView = (TextView)findViewById(R.id.intake_outtake_view_id);
        extraNotesView = (TextView)findViewById(R.id.extra_notes_view_id);

        teamView.setText("Team: "+scout.team);
        teamView.setTypeface(tf);
        drivetrainView.setText("Drivetrain: "+scout.driveTrain);
        drivetrainView.setTypeface(tf);
        liftView.setText("Lift: "+scout.lift);
        liftView.setTypeface(tf);
        intakeOuttakeView.setText("Intake/Outtake: "+scout.intakeOuttake);
        intakeOuttakeView.setTypeface(tf);
        extraNotesView.setText("Extra Notes: "+scout.extraNotes);
        extraNotesView.setTypeface(tf);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
