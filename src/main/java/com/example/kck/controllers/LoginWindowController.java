package com.example.kck.controllers;

import com.example.kck.DBMenager;
import com.example.kck.SceneSwitcher;
import com.example.kck.Settings;
import com.example.kck.User;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

public class LoginWindowController {
    @FXML
    TextField loginTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    Text message;
    public void loginButtonAction(ActionEvent event) throws IOException {

        String login = loginTextField.getText();
        String password = passwordTextField.getText();

        if (login.isEmpty() || password.isEmpty()) {
            message.setText("Wszystkie pola muszą być wypełnione");
            message.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.millis(3000));
            pause.setOnFinished(event1 -> message.setVisible(false));
            pause.play();
            return;
        }

        DBMenager dbMenager = new DBMenager();
        User user = dbMenager.logInUser(login, password);
        if(user == null){
            message.setText("Błędny login lub hasło");
            message.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.millis(3000));
            pause.setOnFinished(event1 -> message.setVisible(false));
            pause.play();
            return;
        }


        message.setText("Pomyślnie zalogowano, następuje przekierowanie...");
        message.setFill(Color.rgb(51, 204, 51));
        message.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(event1 -> {
            message.setVisible(false);
            Settings.getInstance().setUser(user);
            SceneSwitcher ss = new SceneSwitcher();
            ss.switchScene("MainWindow.fxml");
        });
        pause.play();
    }

    public void returnButtonAction(ActionEvent event) throws IOException {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("StartWindow.fxml");
    }
}
