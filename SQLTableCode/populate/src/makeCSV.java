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
/*
try {
      File myObj = new File("filename.txt");
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
 */


import java.io.File;
import java.io.IOException;
public class makeCSV {
 // TODO: verify array indices and avoid "off by one" errors
    static void func(){
        int[] drink_arr;
        String[] ice_arr;
        String[] sugar_arr;
        int drink_ind = 0;
        int ice_ind = 0;
        int sugar_ind = 0;
        int weekly_customer_count = 0;
        int purchases = 0;

        // creating & writing to file from w3schools
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

            while(weekly_customer_count > 0){ //and for each customer
                //there are a random number of purchases
                purchases = math.random(1,5);

                while(purchases > 0){
                    drink_ind = math.random(0,19);
                    ice_ind = math.random(0,2);
                    sugar_ind = math.random(0,2);

                    purchases--;
                }
                weekly_customer_count--;
            }
        }
    }

}

/*
example csv
int, double, string, string
ID,  Total,  Drink,  Mods
ID,  Total,  Drink,  Mods
ID,  Total,  Drink,  Mods
ID,  Total,  Drink,  Mods
 */