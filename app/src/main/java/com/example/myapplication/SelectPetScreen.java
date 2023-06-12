package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectPetScreen extends AppCompatActivity {
    // Button Declarations
    private Button addPetButton;
    private Button selectPetButton;
    private Button mainMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pet_screen);

        addPetButton = (Button) findViewById(R.id.selectPetAddPetButton);
        selectPetButton = (Button)findViewById(R.id.selectPetPet1Button);
        mainMenuButton = (Button)findViewById(R.id.selectPetMainMenuButton);
        // Set the Pet's name from the class here
        // When there is an instance of the Pet class use getPetName() to set the name to the Button text
        selectPetButton.setText(Pet.getInstance().getPetName());
        if(Pet.getInstance().getPetName() == "Add Pet First") {
            addPetButton.setText("Add Pet");
        }
        else{
            addPetButton.setText("Edit Pet Info");
        }
        // Button to navigate to Add a Pet

        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPetScreen();
            }
        });
        selectPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPetInfoScreen();
            }
        });
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenuScreen();
            }
        });
    }

    // Function to return to Main Menu
    private void openMainMenuScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Function to open Add Pet Screen
    public void openAddPetScreen() {
        Intent intent = new Intent(this, AddPetScreen.class);
        startActivity(intent);
    }

    // Function to open Select Pet Screen
    public void openPetInfoScreen() {
        // This restricts the Pet Info screen from being accessed unless a Pet is added first
        if(Pet.getInstance().getPetName() != "Add Pet First"){
            Intent intent = new Intent(this, PetInfoScreen.class);
            startActivity(intent);
        }
    }
}