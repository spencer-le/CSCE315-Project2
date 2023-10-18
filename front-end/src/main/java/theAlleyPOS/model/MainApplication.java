package theAlleyPOS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Sebastian Oberg
 */
public class
MainApplication extends Application {
    /**
     * This start function is what initiates the entire system
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/theAlleyPOS/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setTitle("The Alley POS System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This launches the main program
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}