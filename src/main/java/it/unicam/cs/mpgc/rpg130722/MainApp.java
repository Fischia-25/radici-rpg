package it.unicam.cs.mpgc.rpg130722;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Radici RPG");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 640, 480);

        primaryStage.setTitle("Radici");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}