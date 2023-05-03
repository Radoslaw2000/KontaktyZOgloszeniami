package com.example.kck.controllers;

import com.example.kck.*;
import javafx.animation.PauseTransition;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    Text tytul, login, nameText, surnameText, emailText, townText, phoneNumberText, streetText, homeNumberText;

    @FXML
    TextArea descriptionTextArea;

    private Kontakt kontakt;

    private boolean isDeleted;

    public void dodajKontaktButtonAction(MouseEvent event) {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("DodajKontaktWindow.fxml");
    }

    public void ulubioneButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().setFavourite(true);
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("MainWindow.fxml");
    }

    public void editButtonAction(ActionEvent event) throws IOException {
        System.out.println("Edit");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kck/EdytujKontaktWindow.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root, sceneWidth, sceneHeight);
            stage.setScene(scene);

            EdytujKontaktController controller = loader.getController();
            controller.initialize(kontakt);
            stage.show();
        });
    }

    public void deleteButtonAction(ActionEvent event) throws IOException {
        tytul.setFill(Color.rgb(51, 204, 51));
        DBMenager dbMenager = new DBMenager();
        PauseTransition pause = new PauseTransition(Duration.millis(1500));
        pause.setOnFinished(event1 -> tytul.setVisible(false));

        if(!isDeleted){
            dbMenager.deleteContact(kontakt.getId());
            tytul.setText("Usunięto kontakt");
            tytul.setVisible(true);
            isDeleted = true;
            ////aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        }
        else{
            dbMenager.przywrocContact(kontakt);
            tytul.setText("Przywrócono kontakt");
            tytul.setVisible(true);
            isDeleted = false;
        }
        pause.play();

    }

    public void likeButtonAction(ActionEvent event) throws IOException {
        System.out.println("Like");
        DBMenager dbMenager = new DBMenager();
        PauseTransition pause = new PauseTransition(Duration.millis(1500));
        pause.setOnFinished(event1 -> tytul.setVisible(false));

        if(dbMenager.isFavoriteContact(Settings.getInstance().getUser(), kontakt.getId())){
            dbMenager.deleteFavouriteContact(kontakt.getId());
            tytul.setFill(Color.rgb(234, 27, 48));
            tytul.setText("Usunięto z ulubionych");
            tytul.setVisible(true);
        }
        else{
            dbMenager.insertUlubione(Settings.getInstance().getUser(), kontakt);
            tytul.setFill(Color.rgb(51, 204, 51));
            tytul.setText("Dodano do ulubionych");
            tytul.setVisible(true);
        }
        pause.play();
    }


    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event, gear);
    }
    public void powrotButtonAction(ActionEvent event) {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("MainWindow.fxml");
    }

    public void initialize(Kontakt kontakt) {
        isDeleted = false;
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
