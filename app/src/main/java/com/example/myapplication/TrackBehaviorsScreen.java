package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class TrackBehaviorsScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // String to store the choice of behavior
    private String behaviorChoice;
    // Initialize the behavior spinner
    private Spinner behaviorSpinner;

    private Button addBehaviorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_behaviors_screen);

        behaviorSpinner = findViewById(R.id.behaviorSpinner);
        addBehaviorButton = (Button)findViewById(R.id.addBehaviorButton);

        ArrayAdapter<CharSequence> behaviorAdapter = ArrayAdapter.createFromResource(this, R.array.Behaviors, android.R.layout.simple_spinner_dropdown_item);
        behaviorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        behaviorSpinner.setAdapter(behaviorAdapter);
        behaviorSpinner.setOnItemSelectedListener(this);

        addBehaviorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackBehavior();
            }
        });

    }

    private void trackBehavior() {
        if(behaviorChoice == ""){
            Toast.makeText(getApplicationContext(), "Error: Behavior not tracked", Toast.LENGTH_SHORT).show();
        }
        else{
            Behavior.getInstance().trackBehavior(behaviorChoice);
            Toast.makeText(getApplicationContext(), "Behavior successfully tracked", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        behaviorChoice = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        behaviorChoice = "";
    }
}