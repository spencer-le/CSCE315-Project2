public class Main {
    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        connector.connect();

        CSVReader.readCSV("path_to_csv_file.csv");

        DataPopulator populator = new DataPopulator(connector.getConnection());
        populator.populateSalesHistory();
        populator.populateInventoryItems();

        // TODO: Execute SQL queries here
        makeCSV test = new makeCSV();
        connector.disconnect();
    }
}
