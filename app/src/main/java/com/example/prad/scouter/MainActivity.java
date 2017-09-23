package com.example.prad.scouter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Scout> scouts;
    Toolbar toolbar;
    ListView scoutList;
    ArrayAdapter<Scout> scoutArrayAdapter;
    Button clearButton;
    File path;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Lato-Medium.ttf");

        toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        toolbar.setTitle("Scouter");
        TextView tv = (TextView)toolbar.getChildAt(0);
        tv.setTypeface(tf);

        setSupportActionBar(toolbar);

        path = getFilesDir();
        file = new File(path, "scout_data.txt");
        int len = (int)file.length();
        byte[] bytes = new byte[len];
        try {
            FileInputStream in = new FileInputStream(file);
            try {
                in.read(bytes);
            } finally {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scouts = new ArrayList<Scout>();

        String contents = new String(bytes);
        Log.d("HEY", contents);
        if (!(contents.equals(""))) {
            String[] contentsSplit = contents.split(";");
            Log.d("HEY", contentsSplit.length + "");
            for (String i : contentsSplit) {
                Log.d("HEY", i);
            }
            for (String i : contentsSplit) {
                scouts.add(new Scout(i.split("_")));
            }
            for (int i = 0; i < scouts.size(); i++) {
                if (scouts.get(i).extraNotes.equals("|")) {
                    scouts.set(i, new Scout(new String[]{scouts.get(i).team, scouts.get(i).driveTrain, scouts.get(i).lift, scouts.get(i).intakeOuttake, ""}));
                }
            }
        }

        scoutList = (ListView)findViewById(R.id.scout_list_id);
        scoutArrayAdapter = new ArrayAdapter<Scout>(this, android.R.layout.simple_list_item_1, scouts) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Lato-Medium.ttf");
                ((TextView)view).setTypeface(tf);
                return view;
            }
        };
        scoutList.setAdapter(scoutArrayAdapter);

        scoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent intent = new Intent(MainActivity.this, ViewScoutActivity.class);
                 Bundle scoutData = new Bundle();
                 scoutData.putStringArray("SCOUT_INFO", scouts.get(position).getScoutData());
                 scoutData.putInt("SCOUT_INDEX", position);
                 intent.putExtra("DATA_BUNDLE", scoutData);
                 startActivityForResult(intent, 2);
             }
        });

        clearButton = (Button) findViewById(R.id.clear_button_id);
        clearButton.setTypeface(tf);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = scouts.size()-1; i >= 0; i--) {
                    scouts.remove(i);
                }
                scoutArrayAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_scout_id: {
                Intent intent = new Intent(MainActivity.this, AddNewScoutActivity.class);
                startActivityForResult(intent, 1);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 1: {
                if (resultCode == RESULT_OK) {
                    Bundle results = data.getBundleExtra("DATA_BUNDLE");
                    scouts.add(new Scout(results.getStringArray("SCOUT_INFO")));
                    scoutArrayAdapter.notifyDataSetChanged();
                }
            } break;
            case 2: {
                if (resultCode == RESULT_OK) {
                    Bundle results = data.getBundleExtra("DATA_BUNDLE");
                    if (!(scouts.get(results.getInt("SCOUT_INDEX")).equals(new Scout(results.getStringArray("SCOUT_INFO"))))) {
                        scouts.set(results.getInt("SCOUT_INDEX"), new Scout(results.getStringArray("SCOUT_INFO")));
                        scoutArrayAdapter.notifyDataSetChanged();
                    }
                }
            } break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            FileOutputStream stream = new FileOutputStream(file);
            try {
                for (int i = 0; i < scouts.size(); i++) {
                    stream.write(scouts.get(i).altToString().getBytes());
                    if (i < scouts.size()-1) {
                        stream.write(";".getBytes());
                    }
                }
            } finally {
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
