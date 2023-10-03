import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
public class CSVReader {
    public static void readCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Process the line (e.g., split into fields, transform data)
                // Insert data into the database
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

        /*
        System.out.println("Hello world!");
        //parsing a csv file into Scanner class constructor
        Scanner sc = new Scanner(new File("F:\\CSVDemo.csv"));
        sc.useDelimiter(","); //sets the delimiter pattern
        while(sc.hasNext()){ //returns a boolean value
            System.out.print(sc.next()); //find and returns the next complete token from this scanner
        }
        sc.close(); //closes the scanner
        */
    }
            }
