package com.example.prad.scouter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
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
    int position;

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

        Intent recievedIntent = getIntent();
        scout = new Scout(recievedIntent.getBundleExtra("DATA_BUNDLE").getStringArray("SCOUT_INFO"));
        position = recievedIntent.getBundleExtra("DATA_BUNDLE").getInt("SCOUT_INDEX");

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: {
                Bundle sentData = new Bundle();
                sentData.putStringArray("SCOUT_INFO", scout.getScoutData());
                sentData.putInt("SCOUT_INDEX", position);
                Intent intent = new Intent();
                intent.putExtra("DATA_BUNDLE", sentData);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            }
            case R.id.edit_scout_id: {
                Intent intent = new Intent(ViewScoutActivity.this, EditScoutActivity.class);
                Bundle scoutData = new Bundle();
                scoutData.putStringArray("SCOUT_INFO", scout.getScoutData());
                scoutData.putInt("SCOUT_INDEX", position);
                intent.putExtra("DATA_BUNDLE", scoutData);
                startActivityForResult(intent, 1);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle results = data.getBundleExtra("DATA_BUNDLE");
                    scout = new Scout(results.getStringArray("SCOUT_INFO"));
                    teamView.setText("Team: "+scout.team);
                    drivetrainView.setText("Drivetrain: "+scout.driveTrain);
                    liftView.setText("Lift: "+scout.lift);
                    intakeOuttakeView.setText("Intake/Outtake: "+scout.intakeOuttake);
                    extraNotesView.setText("Extra Notes: "+scout.extraNotes);
                }
        }
    }
}
