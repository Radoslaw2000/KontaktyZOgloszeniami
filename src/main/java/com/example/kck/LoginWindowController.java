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
                Scene scene = new Scene(root, 800, 400);
                stage.setScene(scene);
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            }
            Settings.getInstance().setUser(user);
            stage.show();
        });
        pause.play();
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
