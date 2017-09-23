package com.example.prad.scouter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Prad on 9/23/2017.
 */

public class EditScoutActivity extends AppCompatActivity {

    Toolbar goBackToolbar;
    EditText teamEditText;
    EditText extraNotesEditText;
    Spinner driveTrainSpinner;
    Spinner liftSpinner;
    Spinner intakeOuttakeSpinner;
    int position;

    TextView t1, t2, t3, t4, t5;

    Scout scout;

    String[] driveTrains;
    String[] lifts;
    String[] intakeOuttakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_scout);

        Intent recievedIntent = getIntent();
        scout = new Scout(recievedIntent.getBundleExtra("DATA_BUNDLE").getStringArray("SCOUT_INFO"));
        position = recievedIntent.getBundleExtra("DATA_BUNDLE").getInt("SCOUT_INDEX");

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Lato-Medium.ttf");

        t1 = (TextView)findViewById(R.id.textView);
        t2 = (TextView)findViewById(R.id.textView2);
        t3 = (TextView)findViewById(R.id.textView3);
        t4 = (TextView)findViewById(R.id.textView4);
        t5 = (TextView)findViewById(R.id.textView5);
        t1.setTypeface(tf);
        t2.setTypeface(tf);
        t3.setTypeface(tf);
        t4.setTypeface(tf);
        t5.setTypeface(tf);

        goBackToolbar = (Toolbar)findViewById(R.id.go_back_toolbar_id);
        goBackToolbar.setTitle("Edit team");
        TextView tv = (TextView)goBackToolbar.getChildAt(0);
        tv.setTypeface(tf);
        setSupportActionBar(goBackToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        teamEditText = (EditText)findViewById(R.id.team_edit_text_id);
        teamEditText.setTypeface(tf);
        teamEditText.setText(scout.team);
        extraNotesEditText = (EditText)findViewById(R.id.extra_notes_edit_text_id);
        extraNotesEditText.setTypeface(tf);
        extraNotesEditText.setText(scout.extraNotes);
        driveTrainSpinner = (Spinner)findViewById(R.id.drivetrain_spinner_id);
        liftSpinner = (Spinner)findViewById(R.id.lift_spinner_id);
        intakeOuttakeSpinner = (Spinner)findViewById(R.id.intake_outtake_spinner_id);

        Resources res = getResources();

        driveTrains = res.getStringArray(R.array.drivetrain_choices);
        lifts = res.getStringArray(R.array.lift_choices);
        intakeOuttakes = res.getStringArray(R.array.intake_outtake_choices);

        teamEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                scout.team = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        extraNotesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                scout.extraNotes = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ArrayAdapter<CharSequence> driveTrainAdapter = ArrayAdapter.createFromResource(this, R.array.drivetrain_choices,
                android.R.layout.simple_spinner_item);
        driveTrainAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        driveTrainSpinner.setAdapter(driveTrainAdapter);
        List<String> driveTrainOptions = Arrays.asList((getResources().getStringArray(R.array.drivetrain_choices)));
        driveTrainSpinner.setSelection(driveTrainOptions.indexOf(scout.driveTrain));
        driveTrainSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scout.driveTrain = driveTrains[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                scout.driveTrain = driveTrains[0];
            }
        });

        ArrayAdapter<CharSequence> liftAdapter = ArrayAdapter.createFromResource(this, R.array.lift_choices,
                android.R.layout.simple_spinner_item);
        liftAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        liftSpinner.setAdapter(liftAdapter);
        List<String> liftSpinnerOptions = Arrays.asList((getResources().getStringArray(R.array.lift_choices)));
        liftSpinner.setSelection(liftSpinnerOptions.indexOf(scout.lift));
        liftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scout.lift = lifts[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                scout.lift = lifts[0];
            }
        });

        final ArrayAdapter<CharSequence> intakeOuttakeAdapter = ArrayAdapter.createFromResource(this, R.array.intake_outtake_choices,
                android.R.layout.simple_spinner_item);
        intakeOuttakeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        intakeOuttakeSpinner.setAdapter(intakeOuttakeAdapter);
        List<String> intakeOuttakeOptions = Arrays.asList((getResources().getStringArray(R.array.intake_outtake_choices)));
        intakeOuttakeSpinner.setSelection(intakeOuttakeOptions.indexOf(scout.intakeOuttake));
        intakeOuttakeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scout.intakeOuttake = intakeOuttakes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                scout.intakeOuttake = intakeOuttakes[0];
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: {
                Bundle sentData = new Bundle();
                sentData.putStringArray("SCOUT_INFO", scout.getScoutData());
                sentData.putInt("SCOUT_INDEX", position);
                Intent intent = new Intent();
                intent.putExtra("DATA_BUNDLE",sentData);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
