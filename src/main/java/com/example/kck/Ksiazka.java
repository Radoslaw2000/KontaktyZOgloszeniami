package com.example.kck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Ksiazka extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Ksiazka.class.getResource("StartWindow.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Ksiazka.class.getResource("Filtrowanie.fxml"));
        final double initialSceneWidth = 600;
        final double initialSceneHeight = 500;
        Scene scene = new Scene(fxmlLoader.load(), initialSceneWidth, initialSceneHeight);
        stage.setTitle("Ksiazka telefoniczna");
        stage.setScene(scene);
        stage.setMinHeight(360);
        stage.setMinWidth(640);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}