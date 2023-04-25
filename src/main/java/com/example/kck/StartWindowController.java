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

public class StartWindowController {
    public void loginButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setScene(scene);

        /*
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
         /**/

        stage.show();
    }

    public void registerButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterWindow.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setScene(scene);


        stage.show();
    }

    public void exitButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
