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
import java.io.FileWriter;
import java.io.IOException;

public class makeCSV {
 // TODO: verify array indices and avoid "off by one" errors

    public makeCSV() {
        func();
    }
    static void func(){
        String[] drink_arr = {"Brown Sugar Deerioca Fresh Milk",
        "Cocoa Brown Sugar Deerioca Fresh Milk",
        "Matcha Brown Sugar Deerioca Fresh Milk",
        "Snow Strawberry Lulu",
        "Orange Lulu",
        "Snow Velvet Peach Oolong Tea",
        "Snow Velvet Brown Sugar Latte",
        "Hojicha Peach Oolong Latte",
        "Matcha Peach Oolong Latte",
        "Royal No.9 Tea",
        "Assam Black Tea",
        "Peach Oolong Tea",
        "Royal No.9 Milk Tea",
        "Assam Milk Tea",
        "Garden Milk Tea",
        "Northern Lights"}; //16 drinks 0-15
        // non drink items needing to be included
        // "Morning Dawn"
        //        "Napkins"
        //        "Bags"
        //        "Cup"
        //        "Straws"
        String[] ice_arr   = {"light ice", "regular ice", "extra ice"};
        String[] sugar_arr = {"light sugar", "regular sugar", "extra sugar"};
        int drink_ind = 0;
        int ice_ind = 0;
        int sugar_ind = 0;
        int weekly_customer_count = 0;
        int customer_purchases = 0;
        int purchase_id = 0;
        FileWriter myWriter = null;


        // creating a file [from w3schools]
        try {
            File myObj = new File("year_data_new.csv");

            // this is so a new file actually gets made
            if (myObj.exists()){
                if (myObj.delete()) {
                    System.out.println("File deleted: " + myObj.getName());
                }
            }

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("Failed to create file");
            }

            myWriter = new FileWriter("purchases_data.csv", true); // true for append mode
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        int customer_name_counter = -1;
        for (int days = 1; days <= 365; days++){
            weekly_customer_count = (int) (Math.random() * 901) + 100; //each week generates a random customer count

            while(weekly_customer_count > 0){//and for each customer there are a random number of purchases.
                // NOTE: inside this loop is an order
                customer_purchases = (int) (Math.random() * 5) + 1;
                customer_name_counter++; // each order is only placed by one customer, hence we update the counter once here
                while(customer_purchases > 0){
                    //inside this loop is a single purchase
                    //ints to access arr items at indices
                    drink_ind = (int) (Math.random() * 16); // int between 0 and 15 (inclusive)
                    ice_ind = (int) (Math.random() * 3);    // Generates a random number between 0 and 2 (inclusive)
                    sugar_ind = (int) (Math.random() * 3);  // Generates a random number between 0 and 2 (inclusive)
                    try {
                        // current table setup
                        // id, customer_name, order_date, total_cost, hours
                        // new table setup
                        // id, customer_name, order_date, total_cost


                        myWriter.write(
                                purchase_id + "," + "Customer" + customer_name_counter + "," + days + "," + drink_arr[drink_ind] + "," + ice_arr[ice_ind] + "," +
                                        sugar_arr[sugar_ind] + ","+ (int)(Math.random() * 11.0) + 11,\n");

                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                    }
                    purchase_id++;
                    customer_purchases--;
                }
                weekly_customer_count--;
            }
        }
        try {
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while closing the file.");
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