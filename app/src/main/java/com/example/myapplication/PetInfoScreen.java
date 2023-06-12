package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PetInfoScreen extends AppCompatActivity {

    // Initialize the textView to display to
    TextView petNameDisplay;
    TextView petSpeciesDisplay;
    TextView petBirthdayDisplay;
    TextView petNotesDisplay;
    TextView petHealthDisplay;
    TextView petAgeDisplay;

    private Button selectPetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info_screen);

        petNameDisplay = (TextView)findViewById(R.id.petOverviewName);
        petSpeciesDisplay = (TextView)findViewById(R.id.petInfoSpecies);
        petBirthdayDisplay = (TextView)findViewById(R.id.petInfoBirthday);
        petNotesDisplay = (TextView)findViewById(R.id.petInfoNotes);
        petHealthDisplay = (TextView)findViewById(R.id.petHealthReport);
        petAgeDisplay = (TextView)findViewById(R.id.petInfoPetAge);

        selectPetButton = (Button)findViewById(R.id.searchResultsScreenSearchButton);

        // Display the Pet Name in the Overview, prompt the user to add Pet first if they haven't already
        petNameDisplay.setText("Overview for " + Pet.getInstance().getPetName());

        petSpeciesDisplay.setText(Pet.getInstance().getSpecies());
        petBirthdayDisplay.setText(Pet.getInstance().getPetName() + " was born on " + Pet.getInstance().getBirthDay());
        petNotesDisplay.setText(Pet.getInstance().getNotes());
        petAgeDisplay.setText(Pet.getInstance().getPetName() + " is " + Pet.getInstance().getAge());

        // This will be replaced with Red, Yellow, or Green
        petHealthDisplay.setText(Behavior.getInstance().generalHealthReport());

        selectPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectPetScreen();
            }
        });

    }

    private void openSelectPetScreen() {
        Intent intent = new Intent(this, SelectPetScreen.class);
        startActivity(intent);
    }
}