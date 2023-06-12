package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchResultsScreen extends AppCompatActivity {

    private Button searchButton;
    private TextView resultsDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_screen);

        searchButton = (Button)findViewById(R.id.searchResultsScreenSearchButton);
        resultsDisplay = (TextView)findViewById(R.id.searchResultsScreenResults);

        resultsDisplay.setText(Behavior.getInstance().getSearchResults());

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchScreen();
            }
        });
    }

    private void openSearchScreen() {
        Intent intent = new Intent(this, SearchScreen.class);
        startActivity(intent);
    }
}