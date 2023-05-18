package com.example.assig3;


import DataAcces.ClientDAO;
import Model.Client;
import Presentation.StartScene;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static Scene scene;
    Connection connection = null;

    @SuppressWarnings("deprecation")
    @Override
    public void start(Stage primaryStage) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//        } catch (Exception ex) {
//            System.err.println("An Exception occured during JDBC Driver loading." + " Details are provided below:");
//            ex.printStackTrace(System.err);
//        }
//        ResultSetMetaData rsmd = null;
//        try {
//            connection = DriverManager
//                    .getConnection("jdbc:mysql://localhost:3306/assig3db?user=root&password=12mii2020");
//        } catch (SQLException sqlex) {
//            System.err.println("An SQL Exception occured. Details are provided below:");
//            sqlex.printStackTrace(System.err);
//        }
        try {
            primaryStage.setTitle("UTCN");
            scene = StartScene.getStartScene(primaryStage);
            // scene = Admin.getAdminScene(false);
            // scene = Profesor.getProfScene();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                }
//                connection = null;
//            }
            // Save file
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}