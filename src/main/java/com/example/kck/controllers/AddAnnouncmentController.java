package com.example.kck.controllers;

import com.example.kck.*;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
import java.util.Set;

public class AddAnnouncmentController implements Initializable {
    @FXML
    StackPane message1,message2, message3, message4;
    @FXML
    TextField titleTextField, priceTextField, phoneNumberTextField, townTextField;
    @FXML
    Text tytul;
    @FXML
    ComboBox<String> categoryComboBox, voivodeshipComboBox;
    @FXML
    TextArea descriptionTextArea;
    @FXML
    GridPane userPanel;
    @FXML
    ImageView gear;
    @FXML
    Text login;

    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event,gear);
    }
    public void anulujButtonAction(ActionEvent event) throws IOException {
        Settings.getInstance().switchScene("MainWindowAnnouncments.fxml");
    }

    public void homeButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().setCategory(null);
        Settings.getInstance().getAnnouncmentsFilterSettings().clear();
        Settings.getInstance().switchScene("MainWindowAnnouncments.fxml");
    }


    public void dodajOgloszenieButtonAction(MouseEvent event) {
        Settings.getInstance().switchScene("AddAnnouncmentWindow.fxml");
    }

    public void ulubioneButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().setFavourite(true);
        Settings.getInstance().switchScene("MainWindowAnnouncments.fxml");
    }

    private void messageDisplay(StackPane message){
        message.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.millis(1500));
        pause.setOnFinished(event1 -> message.setVisible(false));
        pause.play();
    }
    public void zapiszButtonAction(ActionEvent event) throws IOException {
        String title = titleTextField.getText();
        String price = priceTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String town = townTextField.getText();
        String voivodeship = (voivodeshipComboBox.getSelectionModel().getSelectedItem() == null) ? "cała polska" : voivodeshipComboBox.getSelectionModel().getSelectedItem();
        String description = descriptionTextArea.getText();
        String category = (categoryComboBox.getSelectionModel().getSelectedItem() == null) ? "inne" : categoryComboBox.getSelectionModel().getSelectedItem();


        if(title.isEmpty()){
            messageDisplay(message1);
            return;
        }
        if(price.isEmpty()){
            messageDisplay(message2);
            return;
        }
        if(phoneNumber.isEmpty()){
            messageDisplay(message3);
            return;
        }
        if(town.isEmpty()){
            messageDisplay(message4);
            return;
        }

        Announcment announcment = new Announcment(1, price, title, description, town, category, phoneNumber, voivodeship);
        //baza
        DBMenager dbMenager = new DBMenager();
        dbMenager.insertAnnouncment(announcment);
        tytul.setText("Dodano ogłoszenie do bazy");
        tytul.setFill(Color.rgb(51, 204, 51));

        PauseTransition pause = new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(event1 -> {
            Settings.getInstance().switchScene("MainWindowAnnouncments.fxml");
        });
        pause.play();

    }

    private void addCategoriesToCombobox() {
        ObservableList<String> categories = categoryComboBox.getItems();
        categories.addAll("Wszystko", "Budownictwo", "Dom i ogród", "Transport", "Elektronika", "Edukacja", "Praca", "Biznes", "Rolnictwo", "Motoryzacja", "Turystyka", "Zdrowie", "Uroda", "Uroczystości", "Poznajmy się");
        categoryComboBox.setItems(categories);

        ObservableList<String> voievodeships = voivodeshipComboBox.getItems();
        voievodeships.addAll("cała polska", "dolnośląskie", "kujawsko-pomorskie", "lubelskie", "lubuskie", "łódzkie", "małopolskie", "mazowieckie", "opolskie", "podkarpackie", "podlaskie", "pomorskie", "śląskie", "świętokrzyskie", "warmińsko-mazurskie", "wielkopolskie", "zachodniopomorskie");
        voivodeshipComboBox.setItems(voievodeships);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCategoriesToCombobox();
        userPanel.add(new LetterCircle(Settings.getInstance().getUser().getLogin().charAt(0),17), 0, 0);
        login.setText(Settings.getInstance().getUser().getLogin());
    }
}
