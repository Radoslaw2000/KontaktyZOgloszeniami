package com.example.kck.controllers;

import com.example.kck.*;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ShowContactDetailsController {

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

    private Contact contact;

    private boolean isDeleted;


    public void homeButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().switchScene("MainWindowContacts.fxml");
    }

    public void dodajKontaktButtonAction(MouseEvent event) {
        Settings.getInstance().switchScene("AddContactWindow.fxml");
    }

    public void ulubioneButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().setFavourite(true);
        Settings.getInstance().switchScene("MainWindowContacts.fxml");
    }

    public void editButtonAction(ActionEvent event) throws IOException {
        System.out.println("Edit");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kck/EditContactWindow.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root, sceneWidth, sceneHeight);
            stage.setScene(scene);

            EditContactController controller = loader.getController();
            controller.initialize(contact);
            stage.show();
        });
    }

    public void deleteButtonAction(ActionEvent event) throws IOException {
        tytul.setFill(Color.rgb(51, 204, 51));
        DBMenager dbMenager = new DBMenager();
        PauseTransition pause = new PauseTransition(Duration.millis(1500));
        pause.setOnFinished(event1 -> tytul.setVisible(false));

        if(!isDeleted){
            dbMenager.deleteContact(contact.getId());
            tytul.setText("Usunięto kontakt");
            tytul.setVisible(true);
            isDeleted = true;
            deleteButton.getStyleClass().clear();
            deleteButton.getStyleClass().add("edit-button");
            deleteButton.setText("Przywróć");
            ////aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        }
        else{
            dbMenager.restoreContact(contact);
            tytul.setText("Przywrócono kontakt");
            tytul.setVisible(true);
            isDeleted = false;
            deleteButton.getStyleClass().clear();
            deleteButton.getStyleClass().add("delete-button");
            deleteButton.setText("Usuń");
        }
        pause.play();

    }

    public void likeButtonAction(ActionEvent event) throws IOException {
        System.out.println("Like");
        DBMenager dbMenager = new DBMenager();
        PauseTransition pause = new PauseTransition(Duration.millis(1500));
        pause.setOnFinished(event1 -> tytul.setVisible(false));

        if(dbMenager.isFavoriteContact(Settings.getInstance().getUser(), contact.getId())){
            dbMenager.deleteFavouriteContact(contact.getId());
            tytul.setFill(Color.rgb(234, 27, 48));
            tytul.setText("Usunięto z ulubionych");
            tytul.setVisible(true);
        }
        else{
            dbMenager.insertFavouriteContact(Settings.getInstance().getUser(), contact);
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
        Settings.getInstance().switchScene("MainWindowContacts.fxml");
    }

    public void initialize(Contact contact) {
        isDeleted = false;
        this.contact = contact;
        userPanel.add(new LetterCircle(Settings.getInstance().getUser().getLogin().charAt(0),17), 0, 0);
        login.setText(Settings.getInstance().getUser().getLogin());

        nameText.setText(contact.getName());
        surnameText.setText(contact.getSurname());
        emailText.setText(contact.getEmail());
        townText.setText(contact.getTown());
        phoneNumberText.setText(contact.getPhoneNumber());
        streetText.setText(contact.getStreet());
        homeNumberText.setText(contact.getHouseNumber());
        descriptionTextArea.setText(contact.getDescription());

        if(Settings.getInstance().getUser().isAdmin()){
            editButton.setVisible(true);
            deleteButton.setVisible(true);
        }
    }
}
