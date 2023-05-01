package com.example.kck.controllers;

import com.example.kck.DBMenager;
import com.example.kck.GearOptions;
import com.example.kck.SceneSwitcher;
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


    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event,gear);
    }

    public void anulujButtonAction(ActionEvent event) throws IOException {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("MainWindow.fxml");
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
            SceneSwitcher ss = new SceneSwitcher();
            ss.switchScene("MainWindow.fxml");
        });
        pause.play();

    }


}
