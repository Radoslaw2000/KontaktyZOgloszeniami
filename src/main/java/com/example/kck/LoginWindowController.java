package com.example.kck;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWindowController {
    public void loginButtonAction(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = loader.load();

        if(sceneWidth > 900){
            Scene scene = new Scene(root, sceneWidth, sceneHeight);
            stage.setScene(scene);
        }
        else {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            stage.show();
        }

    }

    public void returnButtonAction(ActionEvent event) throws IOException {
        // Pobranie sceny z przycisku
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        // Wczytanie nowej sceny z pliku FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartWindow.fxml"));
        Parent root = loader.load();

        // Ustawienie nowej sceny
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setScene(scene);
        stage.show();
    }
}
