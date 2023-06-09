package com.example.kck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class PhoneBook extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhoneBook.class.getResource("StartWindow.fxml"));
        final double initialSceneWidth = 800;
        final double initialSceneHeight = 500;
        Scene scene = new Scene(fxmlLoader.load(), initialSceneWidth, initialSceneHeight);
        stage.setTitle("Ksiazka telefoniczna");
        stage.getIcons().add(new Image(getClass().getResource("titleLogo.png").toExternalForm()));
        stage.setScene(scene);
        stage.setMinHeight(360);
        stage.setMinWidth(650);

        Settings.getInstance().setStage(stage);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}