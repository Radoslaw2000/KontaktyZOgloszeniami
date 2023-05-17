package com.example.kck.controllers;

import com.example.kck.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ShowAnnouncmentDetailsController {

    @FXML
    Button editButton, deleteButton, likeButton;

    @FXML
    ImageView gear;

    @FXML
    GridPane userPanel;

    @FXML
    Text login, titleText, priceText, categoryText, phoneNumberText, townText, voivodeshipText;
    @FXML
    TextArea descriptionTextArea;

    private Announcment announcment;

    private boolean isDeleted;

    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event, gear);
    }

    public void powrotButtonAction(ActionEvent event) throws IOException {
        Settings.getInstance().switchScene("MainWindowAnnouncments.fxml");

    }


    public void homeButtonAction(MouseEvent mouseEvent) {
    }

    public void dodajOgloszenieButtonAction(MouseEvent mouseEvent) {
    }

    public void ulubioneButtonAction(MouseEvent mouseEvent) {
    }

    public void editButtonAction(ActionEvent event) {
    }

    public void likeButtonAction(ActionEvent event) {
    }

    public void deleteButtonAction(ActionEvent event) {
    }

    public void initialize(Announcment announcment) {
        isDeleted = false;
        this.announcment = announcment;
        userPanel.add(new LetterCircle(Settings.getInstance().getUser().getLogin().charAt(0),17), 0, 0);
        login.setText(Settings.getInstance().getUser().getLogin());

        //Text login, titleText, priceText, categoryText, phoneNumberText, townText, voivodeshipText;
        titleText.setText(announcment.getTitle());
        priceText.setText(announcment.getPrice() + " zł");
        categoryText.setText(announcment.getCategory());
        phoneNumberText.setText(announcment.getPhoneNumber());
        townText.setText(announcment.getTown());
        voivodeshipText.setText(announcment.getVoivodeship());
        descriptionTextArea.setText(announcment.getDescription());

        if(Settings.getInstance().getUser().isAdmin()){
            editButton.setVisible(true);
            deleteButton.setVisible(true);
        }
    }
}
