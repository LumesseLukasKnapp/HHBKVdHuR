package hhbkvdhur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HHBKVdUHuRStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/sample.fxml"));
        primaryStage.setTitle("HHBK Verwaltung der Hardware und Räume");
        primaryStage.setScene(new Scene(root, 1000, 775));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

