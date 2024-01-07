package com.endproject.javafundamentalsendproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApplication extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/endproject/javafundamentalsendproject/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add((this.getClass().getResource("/css/login.css")).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Umit's Music Dungeon-Login");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}