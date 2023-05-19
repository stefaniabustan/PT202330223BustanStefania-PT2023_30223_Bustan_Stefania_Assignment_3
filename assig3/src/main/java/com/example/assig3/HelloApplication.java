package com.example.assig3;



import Presentation.StartScene;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;



import java.sql.Connection;


/**
 * aceasata este clasa pentru rularea programului
 */
public class HelloApplication extends Application {
    public static Scene scene;
    Connection connection = null;

    @SuppressWarnings("deprecation")
    @Override
    public void start(Stage primaryStage) {

        try {
            primaryStage.setTitle("UTCN");
            scene = StartScene.getStartScene(primaryStage);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");

        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}