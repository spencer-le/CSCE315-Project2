import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class makeOrderCSV {
        // TODO: verify array indices and avoid "off by one" errors

        public makeOrderCSV() {
            func();
        }
        static void func() {
//            String[] drink_arr = new String[20];
//            String[] ice_arr = {"light ice", "regular ice", "extra ice"};
//            String[] sugar_arr = {"light sugar", "regular sugar", "extra sugar"};
//            int drink_ind = 0;
//            int ice_ind = 0;
//            int sugar_ind = 0;
            int orderNumber = 1;
            int customerName = 1;
            int date = 1;
            float totalCost = 0;
            int day_customer_count = 0;
            int customer_purchases = 0;
//            int purchase_id = 0;
            FileWriter myWriter = null;

            //initialize Drink names:
//            for (int i = 0; i < 20; i++) {
//                drink_arr[i] = "Drink" + i;
//            }


            // creating a file [from w3schools]
            try {
                File myObj = new File("order_year_data.csv");

                // this is so a new file actually gets made
                if (myObj.exists()) {
                    if (myObj.delete()) {
                        System.out.println("File deleted: " + myObj.getName());
                    }
                }

                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("Failed to create file");
                }

                myWriter = new FileWriter("order_year_data.csv", true); // true for append mode
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }


            for (int days = 0; days < 365; days++) {
                day_customer_count = (int) (Math.random() * 100) + 20; //each week generates a random customer count

                while (day_customer_count > 0) {//and for each customer there are a random number of purchases.
                    // NOTE: inside this loop is an order

                    //there are a random number of purchases
                    customer_purchases = (int) (Math.random() * 6) + 1;

                    while (customer_purchases > 0) {
                        totalCost = customer_purchases * 5; // Items will have different prices later
                        try {
                            myWriter.write(
                                    orderNumber + ",Person " + customerName + "," + date + "," + totalCost + "," + ",\n");
                            // Don't close the FileWriter here
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                        }
                        customer_purchases--;
                        orderNumber++;
                        customerName++;
                    }
                    day_customer_count--;
                }
                date++;
            }
            try {
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred while closing the file.");
            }
        }
}
