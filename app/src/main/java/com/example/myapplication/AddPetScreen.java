package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddPetScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button addPetButton;

    private EditText petNameText;
    private EditText petBirthdayText;
    private EditText petNotesText;

    private Spinner petSpeciesSpinner;
    private String speciesChoice;

    private String petNameTextVal;
    private String petBDayTextVal;
    private String petNotesTextVal;

    public Pet pet1;
    //public static final Pet GLOBAL_PET = "com.example.myapplication.GLOBAL_PET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet_screen);

        petNameText = (EditText)findViewById(R.id.addPetNameField);
        petBirthdayText = (EditText)findViewById(R.id.addPetBirthdayField);
        petNotesText = (EditText) findViewById(R.id.addPetNotesField);

        petSpeciesSpinner = findViewById(R.id.addPetSpeciesSpinner);

        ArrayAdapter<CharSequence> speciesAdapter = ArrayAdapter.createFromResource(this, R.array.Species, android.R.layout.simple_spinner_dropdown_item);
        speciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petSpeciesSpinner.setAdapter(speciesAdapter);
        petSpeciesSpinner.setOnItemSelectedListener(this);


        // Button to create Pet in Pet Class and Add
        addPetButton = (Button) findViewById(R.id.addPetAddPetButton);
        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPet();
            }
        });
    }

    // Function to create Pet in Pet class and Add
    public void addPet() {
        // Need to parse variables from text boxes and spinner
        petNameTextVal = petNameText.getText().toString();
        petNotesTextVal = petNotesText.getText().toString();
        petBDayTextVal = petBirthdayText.getText().toString();
        // NOTE: Do not pass in medication, since that is the Veterinarians Job

        // Check for empty parameters
        if (petNameText.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter a Pet Name", Toast.LENGTH_SHORT).show();
        }
        else if (petBirthdayText.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please Enter a Birth Date (YYYY/MM/DD)",Toast.LENGTH_SHORT).show();
        }
        // All necessary values are given
        else {
            if (checkBirthdayValidity() == false){
                Toast.makeText(getApplicationContext(), "Date Incorrect", Toast.LENGTH_SHORT).show();
            }
            else {
                // If there are no notes, set it to the empty string
                if (petNotesText.getText().toString().isEmpty()) {
                    petNotesTextVal = "";
                }
                // Append information to Pet
                pet1 = Pet.getInstance();
                pet1.setPetName(petNameTextVal);
                pet1.setSpecies(speciesChoice);
                pet1.setBirthDay(petBDayTextVal);
                pet1.addNotes(petNotesTextVal);
                Toast.makeText(getApplicationContext(), "Success! " + pet1.getPetName() + " was added", Toast.LENGTH_SHORT).show();
                // This will reopen the Select Pet Screen with the updated name
                Intent intent = new Intent(this, SelectPetScreen.class);
                startActivity(intent);
            }
        }
    }

    // Check if the birthday is in YYYY/MM/DD format
    public boolean checkBirthdayValidity(){
        petBDayTextVal = petBirthdayText.getText().toString();
        String year;
        year = petBDayTextVal.substring(0, 4);

        // Check that the birthday is a valid input
        if(petBDayTextVal.length() != 10){
            Toast.makeText(getApplicationContext(),"Incorrect Date, Please check your input",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (petBDayTextVal.substring(0, 4).matches("-?\\d+") == false) {
            Toast.makeText(getApplicationContext(), "Incorrect Year input", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Integer.valueOf(petBDayTextVal.substring(0, 4)) <= 1989 || Integer.valueOf(petBDayTextVal.substring(0, 4)) >= 2022){
            Toast.makeText(getApplicationContext(), "Incorrect Year input: Please input a Year between 1990 and 2021", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Year has been checked, now check Month
        else if (petBDayTextVal.substring(4, 5).contains("/") == false){
            Toast.makeText(getApplicationContext(),"Forgot the / after Year",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (petBDayTextVal.substring(5, 7).matches("-?\\d+") == false){
            Toast.makeText(getApplicationContext(), "Incorrect Month input", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Integer.valueOf(petBDayTextVal.substring(5, 7)) <= 0 || Integer.valueOf(petBDayTextVal.substring(5, 7)) >= 13){
            Toast.makeText(getApplicationContext(), "Incorrect Month input: Please input a Month between 01 and 12", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Month has been checked, now check Day
        else if (petBDayTextVal.substring(7, 8).contains("/") == false){
            Toast.makeText(getApplicationContext(), "Forgot the / after Month", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (petBDayTextVal.substring(8, 10).matches("-?\\d+") == false){
            Toast.makeText(getApplicationContext(), "Incorrect Day input", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Check for 31 Day Months
        else if (Integer.valueOf(petBDayTextVal.substring(5, 7)) == 1 || Integer.valueOf(petBDayTextVal.substring(5, 7)) == 3 || Integer.valueOf(petBDayTextVal.substring(5, 7)) == 5 || Integer.valueOf(petBDayTextVal.substring(5, 7)) == 7 || Integer.valueOf(petBDayTextVal.substring(5, 7)) == 8 || Integer.valueOf(petBDayTextVal.substring(5, 7)) == 10 || Integer.valueOf(petBDayTextVal.substring(5, 7)) == 12){
            if (Integer.valueOf(petBDayTextVal.substring(8, 10)) <= 0 || Integer.valueOf(petBDayTextVal.substring(8, 10)) >= 32) {
                Toast.makeText(getApplicationContext(), "Incorrect Date for Month input: Please input a Date between 01 and 31", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        // Check for 30 Day Months
        else if (Integer.valueOf(petBDayTextVal.substring(5, 7)) == 4 || Integer.valueOf(petBDayTextVal.substring(5, 7)) == 6 || Integer.valueOf(petBDayTextVal.substring(5, 7)) == 9 || Integer.valueOf(petBDayTextVal.substring(5, 7)) == 11){
            if (Integer.valueOf(petBDayTextVal.substring(8, 10)) <= 0 || Integer.valueOf(petBDayTextVal.substring(8, 10)) >= 31) {
                Toast.makeText(getApplicationContext(), "Incorrect Date for Month input: Please input a Date between 01 and 30", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        // Check for February
        else if (Integer.valueOf(petBDayTextVal.substring(5, 7)) == 2 && (Integer.valueOf(petBDayTextVal.substring(8, 10)) >= 0 || Integer.valueOf(petBDayTextVal.substring(8, 10)) >= 29)){
            Toast.makeText(getApplicationContext(), "Incorrect Date for Month input: Please input a Date between 01 and 28\n NOTE: Input 28 if Pet was born on a leap year 29th", Toast.LENGTH_SHORT).show();
            return false;
        }
        // All checks have been passed: Birthday is correct format
        return true;
    }

    // Set Species based on what is selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        speciesChoice = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}