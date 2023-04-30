package com.example.kck;

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
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
            double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

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
            }
            Settings.getInstance().setUser(new User(login));
            stage.show();
        });
        pause.play();
    }

    public void returnButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartWindow.fxml"));
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
}
