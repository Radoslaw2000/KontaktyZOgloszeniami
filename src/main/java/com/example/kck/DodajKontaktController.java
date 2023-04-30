package com.example.kck;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class DodajKontaktController {

    @FXML
    TextField nameTextField, surnameTextField, emailTextField, townTextField, phoneNumberTextField, streetTextField,homeNumberTextField;
    @FXML
    TextArea descriptionTextArea;
    @FXML
    StackPane message1, message2, message3;

    @FXML
    Text tytul;

    @FXML
    ImageView gear;
    private void logout(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.setMaximized(false);
        stage.show();
    }

    public void gearButtonAction(MouseEvent event) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem importItem = new MenuItem("Importuj kontakty...");
        MenuItem exportItem = new MenuItem("Exportuj kontakty...");
        MenuItem manageItem = new MenuItem("Zarządzaj folderami...");
        MenuItem logOutItem = new MenuItem("Wyloguj");

        logOutItem.setOnAction(e -> {
            logout((Stage) ((Node) event.getSource()).getScene().getWindow());
        });

        contextMenu.getItems().addAll(importItem, exportItem, manageItem, logOutItem);
        contextMenu.show(gear, event.getScreenX(), event.getScreenY());
    }

    public void anulujButtonAction(ActionEvent event) throws IOException {
        // Pobranie sceny z przycisku
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        // Wczytanie nowej sceny z pliku FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = loader.load();

        // Ustawienie nowej sceny
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setScene(scene);

        stage.show();
    }

    private void messageDisplay(StackPane message){
        message.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.millis(1500));
        pause.setOnFinished(event1 -> message.setVisible(false));
        pause.play();
    }
    public void zapiszButtonAction(ActionEvent event) throws IOException {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String email = emailTextField.getText();
        String town = townTextField.getText();
        String street = streetTextField.getText();
        String homeNumber = homeNumberTextField.getText();
        String description = descriptionTextArea.getText();

        if(surname.isEmpty()){
            messageDisplay(message1);
            return;
        }
        if(phoneNumber.isEmpty()){
            messageDisplay(message2);
            return;
        }
        if(town.isEmpty()){
            messageDisplay(message3);
            return;
        }

        DBMenager dbMenager = new DBMenager();
        dbMenager.insertContact(name, surname, phoneNumber, email, town, street, homeNumber, description);
        tytul.setText("Dodano kontakt do bazy");
        tytul.setFill(Color.rgb(51, 204, 51));

        PauseTransition pause = new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(event1 -> {
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
            stage.show();
        });
        pause.play();

    }


}
