package net.metryumora.passgen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/passwordGenerator.fxml"));
        primaryStage.setTitle("Password Generator");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(event -> primaryStage.close());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
