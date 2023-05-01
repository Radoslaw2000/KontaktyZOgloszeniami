package com.example.kck.controllers;

import com.example.kck.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WyswietlKontaktController{

    @FXML
    Button editButton, deleteButton, likeButton;

    @FXML
    ImageView gear;

    @FXML
    GridPane userPanel;

    @FXML
    Text login, nameText, surnameText, emailText, townText, phoneNumberText, streetText, homeNumberText;

    @FXML
    TextArea descriptionTextArea;

    private Kontakt kontakt;

    public void dodajKontaktButtonAction(MouseEvent event) {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("DodajKontaktWindow.fxml");
    }

    public void editButtonAction(ActionEvent event) throws IOException {
        System.out.println("Edit");
    }

    public void deleteButtonAction(ActionEvent event) throws IOException {
        System.out.println("Delete");
        DBMenager dbMenager = new DBMenager();
        dbMenager.deleteContact(kontakt.getId());
    }

    public void likeButtonAction(ActionEvent event) throws IOException {
        System.out.println("Like");
        DBMenager dbMenager = new DBMenager();

        if(dbMenager.isFavoriteContact(Settings.getInstance().getUser(), kontakt.getId())){
            dbMenager.deleteFavouriteContact(kontakt.getId());
        }
        else{
            dbMenager.insertUlubione(Settings.getInstance().getUser(), kontakt);
        }
    }

    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event, gear);
    }
    public void powrotButtonAction(ActionEvent event) {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("MainWindow.fxml");
    }

    public void initialize(Kontakt kontakt) {
        this.kontakt = kontakt;
        userPanel.add(new LetterCircle(Settings.getInstance().getUser().getLogin().charAt(0),17), 0, 0);
        login.setText(Settings.getInstance().getUser().getLogin());
        nameText.setText(kontakt.getImie());
        surnameText.setText(kontakt.getNazwisko());
        emailText.setText(kontakt.getEmail());
        townText.setText(kontakt.getMiejscowosc());
        phoneNumberText.setText(kontakt.getNrTelefonu());
        streetText.setText(kontakt.getUlica());
        homeNumberText.setText(kontakt.getNrDomu());
        descriptionTextArea.setText(kontakt.getOpis());

        if(Settings.getInstance().getUser().isAdmin()){
            editButton.setVisible(true);
            deleteButton.setVisible(true);
        }

    }
}
