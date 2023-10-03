import java.io.*;
import java.util.Scanner;
public class ReadCSVExample{
    public static void main(String[] args) throws Exception{
        //parsing a csv file into Scanner class constructor
        Scanner sc = new Scanner(new File("F:\\CSVDemo.csv"));
        sc.useDelimiter(","); //sets the delimiter pattern
        while(sc.hasNext()){ //returns a boolean value
            System.out.print(sc.next()); //find and returns the next complete token from this scanner
        }
        sc.close(); //closes the scanner
    }
}