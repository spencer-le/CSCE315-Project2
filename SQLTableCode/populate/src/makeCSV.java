/*
brainstorming
Requirements:
- 52 weeks
- ~1 million in sales
- 2 peak days in the first few weeks
- 20 inventory items

Strategy:
Each week there are X people
    - math.random(100,1000) people visiting
Each person makes Y purchases
    - math.random(1,5) items purchased per person
Each purchase consists of
    - drink_arr[ random index ] size 20
    - ice_arr  [ random index ] size 3  [none, light ice, extra ice]
    - sugar_arr[ random index ] size 3  [none, light sugar, extra sugar]

 */



import java.io.File;
import java.io.IOException;
public class makeCSV {
 // TODO: verify array indices and avoid "off by one" errors

    public makeCSV() {
        func();
    }
    static void func(){
        //the arrays store all options for ordering a drink + modifiers
        // the ind variables will store random indices for custom drinks
        String[] drink_arr = {"Drink1", "Drink2", "Drink3", "Drink4", "Drink5", "Drink6",
                "Drink7", "Drink8", "Drink9", "Drink10", "Drink11", "Drink12", "Drink13",
                "Drink14", "Drink15", "Drink16", "Drink17", "Drink18", "Drink19", "Drink20" };
        String[] ice_arr   = {"light ice", "regular ice", "extra ice"};
        String[] sugar_arr = {"light sugar", "regular sugar", "extra sugar"};
        int drink_ind = 0;
        int ice_ind = 0;
        int sugar_ind = 0;
        int weekly_customer_count = 0;
        int customer_purchases = 0;
        int purchase_id = 0;
        // creating a file [from w3schools]
        try {
            File myObj = new File("year_data.csv");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            //e.printStackTrace(); this was from w3schools, but IDE says its not robust enough
        }

        for (int weeks = 0; weeks < 52; weeks++){
            weekly_customer_count = math.random(100,1000); //each week generates a random customer count

            while(weekly_customer_count > 0){
                //and for each customer there are a random number of purchases
                customer_purchases = math.random(1,5);

                while(customer_purchases > 0){
                    //random indices are decided which are the options to make a custom drink order
                    drink_ind = math.random(0,19);
                    ice_ind   = math.random(0,2);
                    sugar_ind = math.random(0,2);
                    try { // writing to a file [from w3schools]
                        FileWriter myWriter = new FileWriter("year_data.csv");
                        myWriter.write(
                                purchase_id + "," + drink_arr[drink_ind] + "," + ice_arr[ice_ind] + "," +
                                sugar_arr[sugar_ind] + ",\n");
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                    }
                    purchase_id++;
                    customer_purchases--;
                }
                weekly_customer_count--;
            }
        }
    }
}

/*
example csv (for explanation purposes)
int, double, string, string
ID,  Total,  Drink,  Mods
ID,  Total,  Drink,  Mods
ID,  Total,  Drink,  Mods
ID,  Total,  Drink,  Mods
 */