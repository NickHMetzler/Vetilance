package com.example.myapplication;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.time.temporal.ChronoUnit;

public class Pet {

    // Variable Declarations
    private String name;
    private String species;
    private String bDay;
    private String notes = "Notes: ";
    // Replace with a clock
    // Find a way to calculate age based on time-birthday
    private long millis=System.currentTimeMillis();
    //Create an array of medications

    // Create a Singleton, by creating it here we avoid race conditions and prevent any possible multithreading issues
    private static final Pet INSTANCE = new Pet("Add Pet First", "", "", "", "", 0, "");
    // Creation Function (Private for a Singleton)
    private Pet(String petName, String petSpecies, String petBirthDay, String petNotes, String petMedication, int petDosage, String petDosageUnit){
        setPetName(petName);
        setSpecies(petSpecies);
        setBirthDay(petBirthDay);
        addNotes(petNotes);
        addMeds(petMedication, petDosage, petDosageUnit);
    }
    // Return the Instance of Pet
    public static Pet getInstance(){
        return INSTANCE;
    }

    // Set variables functions

    // Set name Function
    public void setPetName(String petName){
        name = petName;
    }

    // Get name Function
    public String getPetName(){
        return name;
    }

    // Set species Function
    public void setSpecies(String petSpecies) {
        species = petSpecies;
    }

    // Get species Function
    public String getSpecies(){
        return species;
    }

    // Set birthday Function
    public void setBirthDay(String petBirthDay) {
        // Convert Birthday to integer
        bDay = petBirthDay;
    }

    public String getBirthDay(){ return bDay; }

    // Get age Function
    public String getAge(){
        // Prevent Fatal error with this if statement
        if(bDay == ""){
            return "N/A";
        }
        else {
            // We calculate the age by taking the BirthDay and subtracting the current time
            java.sql.Date currDate = new java.sql.Date(millis);
            String dateStr = currDate.toString();
            String modStr = dateStr.substring(0, 4) + "/" + dateStr.substring(5, 7) + "/" + dateStr.substring(8, 10);
            // Set the formatter to parse the date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            // Parse the dates from the strings
            LocalDate currentDate = LocalDate.parse(modStr, formatter);
            LocalDate birthDate = LocalDate.parse(bDay, formatter);
            long elapsedTime = ChronoUnit.YEARS.between(birthDate, currentDate);
            if(elapsedTime < 1){
                elapsedTime = ChronoUnit.MONTHS.between(birthDate, currentDate);
                if(elapsedTime < 1){
                    elapsedTime = ChronoUnit.DAYS.between(birthDate, currentDate);
                    return Long.toString(elapsedTime) + " Days Old";
                }
                else{
                    return Long.toString(elapsedTime) + " Months Old";
                }
            }
            else{
                return Long.toString(elapsedTime) + " Years Old";
            }
        }
    }

    // Add notes Function
    public void addNotes(String newNotes){
        // There are no current notes
        if (notes == "Notes: ") {
            notes = notes + newNotes;
        }
        // Make a new line and append the new notes
        else {
            notes = notes + "\n" + newNotes;
        }
    }

    // Get notes Function
    public String getNotes(){
        return notes;
    }

    // Combine Medication Information and add to Array
    public void addMeds(String medName, int medDosage, String unit){
        String medInfo = medName + " : " + medDosage + unit;
    }

    // Get age Function for finding conditions
    public int getAgeBehaviors(){
        // Prevent Fatal error with this if statement
        if(bDay == ""){
            // -1 will let the program know that no date has been set yet
            return -1;
        }
        else {
            // We calculate the age by taking the BirthDay and subtracting the current time
            java.sql.Date currDate = new java.sql.Date(millis);
            String dateStr = currDate.toString();
            String modStr = dateStr.substring(0, 4) + "/" + dateStr.substring(5, 7) + "/" + dateStr.substring(8, 10);
            // Set the formatter to parse the date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            // Parse the dates from the strings
            LocalDate currentDate = LocalDate.parse(modStr, formatter);
            LocalDate birthDate = LocalDate.parse(bDay, formatter);
            long elapsedTime = ChronoUnit.YEARS.between(birthDate, currentDate);
            return (Math.toIntExact(elapsedTime));
            }
        }
    }



