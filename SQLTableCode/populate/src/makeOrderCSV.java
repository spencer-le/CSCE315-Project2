import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class makeOrderCSV {
        // TODO: verify array indices and avoid "off by one" errors

        public makeOrderCSV() {
            func();
        }
        static void func() {
            int orderNumber = 1;
            int customerName = 1;
            int date = 1;
            float totalCost = 0;
            int day_customer_count = 0;
            int customer_purchases = 0;
            FileWriter myWriter = null;

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
                myWriter.write("id, customer_name, order_date, total_cost, hour, \n");
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }


            for (int days = 0; days < 365; days++) {
                if (days == 120 || days == 303) {
                    day_customer_count = 200;
                } else {
                    day_customer_count = (int) (Math.random() * 100) + 20;
                }

                while (day_customer_count > 0) {//and for each customer there are a random number of purchases.
                    // NOTE: inside this loop is an order

                    //there are a random number of purchases
                    customer_purchases = (int) (Math.random() * 6) + 1;
                    int hour = (int) (Math.random() * 11) + 11;

                    while (customer_purchases > 0) {
                        totalCost = customer_purchases * 5; // Items will have different prices later
                        try {
                            myWriter.write(
                                    orderNumber + ",Person " + customerName + "," + date + "," + totalCost + "," + hour + "\n");
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
