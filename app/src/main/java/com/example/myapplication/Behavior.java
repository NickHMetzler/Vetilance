package com.example.myapplication;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Behavior {
    // Used to calculate the time
    private long millis=System.currentTimeMillis();

    // This is used to pass information across the search and results screen
    private String associatedConditions = "";

    // ArrayList containing the conditions that can be searched
    private ArrayList<String> conditions = new ArrayList<>(Arrays.asList("Skin Allergy: Pet possibly exhibits fur loss or skin irritation. Inspect the area affected and remove possible causes (flea collar, plastic bowl, wtc) based on where the are of irritation is. If issue persists all over body, possibly food may be the culprit. Also possibly caused by fleas.\n;associated Behaviors: Scratching", "Fleas: Pet exhibits scratching, and possibly an allergic reaction. Inspect your pets fur and areas of most concern. Treat with topical solution, shampoo, or flea collar\n;Associated Behaviors: Scratching", "Ear Infection: Pet scratches at ear and shakes head. There are many causes. Inspect the pets ear carefully and remove any foreign objects. If the behavior continues. Contact the vet\n;Associated Behaviors: Scratching, Head Shaking", "Acute Gastritis:  Inflammation caused by foreign objects, bad food, food allergy, infections, toxins. If this occurs please contact your veterinarian \n;Associated Behaviors: Loss of Appetite, Vomiting", "Colitis/Enteritis: Stomach or intestine inflammation. Caused by eating table scraps or a parasitic infection. Contact your Vet to prescribe an easily digestible food.\nAssociated Behaviors: Vomiting, Diarrhea, Abnormal Stool","Gastric Ulcers: An ulcer that has developed in the stomach. Can cause blood poisoning if an ulcer causes a stomach perforation.\n;Associated Behaviors: Vomiting, (Can possibly be Asymptomatic)","Gastric Dilation: Stomach bloating causing a distended stomach. Can possibly be fatal. If this occurs contact your veterinarian immediately\n;Associated Behaviors: Lethargy, Excessive Salivation"," Gastric Neoplasia (Tumors): These tumors typically occur in older animals. If this occurs surgery will be necessary. If it doesn't affect the animal's quality of life and is benign, it can be left alone. \n;Associated Behaviors: Vomiting, Weight Loss","Motion Sickness: Can occur if your animal is not used to moving in a vehicle. This condition can go away over time. Your veterinarian can prescribe common motion sickness medication such as Dramamine or Gravol (2-4mg per pound every 8 hours).\n;Associated Behaviors: Excessive Salivation, Vomiting","Hypoadrenocorticism (Addisons Disease): The body's immune system attacks the adrenal glands, which throws off the body regulating hormones. \n;Associated Behaviors: Diarrhea, Vomiting", "Urinary Tract Infection: Animal can have issues or pain while urinating. Homeopathic remedies work well for this condition.\n;Associated Behaviors: Excessive Urination", "Incontinence and Bladder Control Issues: \n;Associated Behaviors: Excessive Urination" ));
    // ," : \n;Associated Behaviors:"

    // Initialize empty array to track behaviors
    private ArrayList<String> behaviors = new ArrayList<>();

    // Create an instance of the class since it need only be used once
    private static final Behavior INSTANCE = new Behavior();

    // Return the Instance of Behavior so we can call functions
    public static Behavior getInstance(){
        return INSTANCE;
    }

    // Public behavior tracking method
    public void trackBehavior(String behavior){
            //purgeOldBehaviors();
            java.sql.Date date = new java.sql.Date(millis);
            String dateStr = date.toString();
            String currTime = dateStr.substring(0, 4) + "/" + dateStr.substring(5, 7) + "/" + dateStr.substring(8, 10);
            behaviors.add(currTime + ":" + behavior);
    }

    public String printBehaviors(){
        return behaviors.get(0);
    }

    // Purge behaviors older than 2 weeks
    private void purgeOldBehaviors() {
        java.sql.Date currDate = new java.sql.Date(millis);
        String dateStr = currDate.toString();
        String modStr = dateStr.substring(0, 4) + "/" + dateStr.substring(5, 7) + "/" + dateStr.substring(8, 10);

        // Iterator is needed to remove items from list while iterating over list
        // If an iem is removed from the list while iterating over it, it could cause a fatal error without iterator
        Iterator itr = behaviors.iterator();

        // Iterate through all the logged behaviors
        while (itr.hasNext()) {
            // Parse the behavior string
            String dateString = (String) itr.next();
            // Set the formatter to parse the date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            // Parse the dates from the strings
            LocalDate currentDate = LocalDate.parse(modStr, formatter);
            LocalDate logDate = LocalDate.parse(dateString, formatter);
            // Calculate the amount of days since that behavior log
            long elapsedDays = ChronoUnit.DAYS.between(logDate, currentDate);

            if(elapsedDays >= 14){
                // Remove item if it is two weeks old or more
                itr.remove();
                }
            }
        }


    // Provide a general health report based on behaviors and time logged
    public String generalHealthReport(){
        // Used to count how many instances there were in the past two weeks
        int severityCounter = 1;
        int conditionCounter = 0;
        String sameDay = "";
        // go through each behavior item in the array to gauge severity
        // indices 10-len are the behavior

        Iterator itr = behaviors.iterator();
        while (itr.hasNext()) {
            String newBehavior = (String) itr.next();
            // Parse the date
            String newBehaviorDate = newBehavior.substring(0, 10);
            if(sameDay != newBehaviorDate) {
                sameDay = newBehaviorDate;
            }
            else{
                severityCounter += 1;
            }
            conditionCounter += 1;
        }

        int finalCount= severityCounter * conditionCounter;

        if(finalCount >= 12){
            return "Pet overall health is: Red\n contact your Vet immediately";
        }
        else if(finalCount >= 5){
            return "Pet overall health is: Yellow\n please continue to monitor " + Pet.getInstance().getPetName();
        }
        else{
            return "Pet overall health is: Green\n" + Pet.getInstance().getPetName() + " is healthy";
        }
    }

    // Return the name and short description of possible conditions
    public void possibleConditionsBehavior(String behavior){
        int tracker = 0;
        // Take the behavior and check in the array of strings if any of the conditions contain the behavior
        for (int index=0; index <= conditions.size() - 1; index++){
            if(conditions.get(index).contains(behavior) && tracker <= 2){
                associatedConditions = associatedConditions + conditions.get(index) + "\n\n";
                tracker += 1;
            }
            else if(tracker >= 3){
                break;
            }
        }
    }

    public String getSearchResults(){
        String results = associatedConditions;
        associatedConditions = "";
        return results;
    }
}
