package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // Button Declarations
    private Button petSelectButton;
    private Button trackBehaviorButton;
    private Button searchConditionsButton;
    private Button callVetButton;
    private Button emailVetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button to navigate to selecting pets
        petSelectButton = (Button) findViewById(R.id.mainSelectPetButton);
        petSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectPetScreen();
            }
        });

        // Button to navigate to tracking behavior
        trackBehaviorButton = (Button) findViewById(R.id.mainTrackBehaviorButton);
        trackBehaviorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrackBehaviorScreen();
            }
        });

        // Button to navigate to searching conditions
        searchConditionsButton = (Button) findViewById(R.id.mainSearchConditionButton);
        searchConditionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchConditionsScreen();
            }
        });

        // Button to call Vet
        callVetButton = (Button) findViewById(R.id.mainCallVetButton);
        callVetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callVet();
            }
        });

        // Button to email Vet
        emailVetButton = (Button) findViewById(R.id.mainEmailVetButton);
        emailVetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailVet();
            }
        });

    }

    // Function to open Select Pet Screen
    public void openSelectPetScreen() {
        Intent intent = new Intent(this, SelectPetScreen.class);
        startActivity(intent);
    }

    // Function to open Track Behaviors Screen
    public void openTrackBehaviorScreen() {
        if(Pet.getInstance().getPetName() != "Add Pet First") {
            Intent intent = new Intent(this, TrackBehaviorsScreen.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Please Add Pet First", Toast.LENGTH_SHORT).show();
        }
    }

    // Function to open Search Conditions Screen
    public void openSearchConditionsScreen() {
        Intent intent = new Intent(this, SearchScreen.class);
        startActivity(intent);
    }

    // Function to open cell and input vet phone number
    public void callVet() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:416-986-0482"));
        startActivity(intent);
    }

    // Function to open open email and input vet email
    public void emailVet() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto: nickfromportugal@gmail.com"));
        emailIntent.setType("text/plain");
        startActivity(emailIntent);
    }

}