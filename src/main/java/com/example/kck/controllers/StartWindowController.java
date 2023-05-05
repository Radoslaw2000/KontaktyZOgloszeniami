package com.example.kck.controllers;

import com.example.kck.SceneSwitcher;
import com.example.kck.Settings;
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
        Settings.getInstance().switchScene("LoginWindow.fxml");
    }

    public void registerButtonAction(ActionEvent event) throws IOException {
        Settings.getInstance().switchScene("RegisterWindow.fxml");
    }

    public void exitButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
