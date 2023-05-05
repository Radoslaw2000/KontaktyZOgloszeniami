package com.example.kck.controllers;

import com.example.kck.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddContactController implements Initializable {

    @FXML
    TextField nameTextField, surnameTextField, emailTextField, townTextField, phoneNumberTextField, streetTextField,homeNumberTextField;
    @FXML
    TextArea descriptionTextArea;
    @FXML
    StackPane message1, message2, message3;

    @FXML
    Text tytul;

    @FXML
    GridPane userPanel;
    @FXML
    ImageView gear;
    @FXML
    Text login;


    public void homeButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().switchScene("MainWindowContacts.fxml");
    }

    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event,gear);
    }

    public void dodajKontaktButtonAction(MouseEvent event) {
        Settings.getInstance().switchScene("AddContactWindow.fxml");
    }

    public void ulubioneButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().setFavourite(true);
        Settings.getInstance().switchScene("MainWindowContacts.fxml");
    }

    public void anulujButtonAction(ActionEvent event) throws IOException {
        Settings.getInstance().switchScene("MainWindowContacts.fxml");
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
            Settings.getInstance().switchScene("MainWindowContacts.fxml");
        });
        pause.play();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userPanel.add(new LetterCircle(Settings.getInstance().getUser().getLogin().charAt(0),17), 0, 0);
        login.setText(Settings.getInstance().getUser().getLogin());
    }

}
