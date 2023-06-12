package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class SearchScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button mainMenuButton;
    private Button searchByBehaviorButton;
    private Button searchByStringButton;
    private Spinner behaviorSpinner;
    public TextView searchString;
    public String searchBehavior;
    public String searchCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        mainMenuButton = (Button)findViewById(R.id.searchResultsScreenSearchButton);
        searchByBehaviorButton = (Button)findViewById(R.id.searchScreenByBehaviorButton);
        searchByStringButton = (Button)findViewById(R.id.normalSearchButton);
        behaviorSpinner = findViewById(R.id.behaviorSpinner);
        searchString = findViewById(R.id.searchScreenStringText);

        ArrayAdapter<CharSequence> behaviorAdapter = ArrayAdapter.createFromResource(this, R.array.Behaviors, android.R.layout.simple_spinner_dropdown_item);
        behaviorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        behaviorSpinner.setAdapter(behaviorAdapter);
        behaviorSpinner.setOnItemSelectedListener(this);

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenuScreen();
            }
        });
        searchByStringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultsScreenString();
            }
        });

        searchByBehaviorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultsScreenBehavior();
            }
        });


    }

    // Function to return to Main Menu
    private void openMainMenuScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Function to return to Main Menu
    private void openResultsScreenBehavior() {
        // Set the return variable to the associated conditions
        Behavior.getInstance().possibleConditionsBehavior(searchBehavior);
        Intent intent = new Intent(this, SearchResultsScreen.class);
        startActivity(intent);
    }

    // Function to return to Main Menu
    private void openResultsScreenString() {
        searchCriteria = searchString.getText().toString();
        Behavior.getInstance().possibleConditionsBehavior(searchCriteria);
        Intent intent = new Intent(this, SearchResultsScreen.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        searchBehavior = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}