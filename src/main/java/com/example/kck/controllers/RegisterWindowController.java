package com.example.kck.controllers;

import com.example.kck.DBMenager;
import com.example.kck.SceneSwitcher;
import com.example.kck.Settings;
import com.example.kck.User;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class RegisterWindowController {

    @FXML
    TextField loginTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    TextField repeatPasswordTextField;
    @FXML
    Text message;

    public void registerButtonAction(ActionEvent event) throws IOException {

        String login = loginTextField.getText();
        String password = passwordTextField.getText();
        String repeatPassword = repeatPasswordTextField.getText();
        DBMenager dbMenager = new DBMenager();

        if (login.isEmpty() || password.isEmpty()) {
            message.setText("Wszystkie pola muszą być wypełnione");
            message.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.millis(3000));
            pause.setOnFinished(event1 -> message.setVisible(false));
            pause.play();
            return;
        }
        if(dbMenager.checkLogin(login)){
            message.setText("Takie konto już istnieje");
            message.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.millis(3000));
            pause.setOnFinished(event1 -> message.setVisible(false));
            pause.play();
            return;
        }
        if (!password.equals(repeatPassword)) {
            message.setText("Hasła nie są identyczne");
            message.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.millis(3000));
            pause.setOnFinished(event1 -> message.setVisible(false));
            pause.play();
            return;
        }

        dbMenager.insertUser(login, password);
        message.setText("Pomyślnie zarejestrowano, następuje przekierowanie...");
        message.setFill(Color.rgb(51, 204, 51));
        message.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(event1 -> {
            message.setVisible(false);
            Settings.getInstance().setUser(new User(login));
            Settings.getInstance().switchScene("MainWindowContacts.fxml");
        });
        pause.play();
    }

    public void returnButtonAction(ActionEvent event) throws IOException {
        Settings.getInstance().switchScene("StartWindow.fxml");
    }
}
