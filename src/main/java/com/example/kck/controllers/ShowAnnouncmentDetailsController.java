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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ShowAnnouncmentDetailsController {

    @FXML
    Button editButton, deleteButton, likeButton;

    @FXML
    ImageView gear;

    @FXML
    GridPane userPanel;

    @FXML
    Text login, tytul, titleText, priceText, categoryText, phoneNumberText, townText, voivodeshipText;
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

    public void deleteButtonAction(ActionEvent event) throws IOException {
        tytul.setFill(Color.rgb(51, 204, 51));
        DBMenager dbMenager = new DBMenager();
        PauseTransition pause = new PauseTransition(Duration.millis(1500));
        pause.setOnFinished(event1 -> tytul.setVisible(false));

        if(!isDeleted){
            dbMenager.deleteAnnouncment(announcment.getId());
            tytul.setText("Usunięto kontakt");
            tytul.setVisible(true);
            isDeleted = true;
            deleteButton.getStyleClass().clear();
            deleteButton.getStyleClass().add("edit-button");
            deleteButton.setText("Przywróć");
        }
        else{
            dbMenager.restoreAnnouncment(announcment);
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

        if(dbMenager.isFavoriteAnnouncment(Settings.getInstance().getUser(), announcment.getId())){
            dbMenager.deleteFavouriteAnnouncment(announcment.getId());
            tytul.setFill(Color.rgb(234, 27, 48));
            tytul.setText("Usunięto z ulubionych");
            tytul.setVisible(true);
        }
        else{
            dbMenager.insertFavouriteAnnouncment(Settings.getInstance().getUser(), announcment);
            tytul.setFill(Color.rgb(51, 204, 51));
            tytul.setText("Dodano do ulubionych");
            tytul.setVisible(true);
        }
        pause.play();
    }


    public void homeButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().setFavourite(false);
        Settings.getInstance().setCategory(null);
        Settings.getInstance().switchScene("MainWindowAnnouncments.fxml");
    }

    public void dodajOgloszenieButtonAction(MouseEvent mouseEvent) {
        Settings.getInstance().switchScene("AddAnnouncmentWindow.fxml");
    }

    public void ulubioneButtonAction(MouseEvent mouseEvent) {
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().setFavourite(true);
        Settings.getInstance().setCategory("wszystko");
        Settings.getInstance().switchScene("MainWindowAnnouncments.fxml");
    }

    public void editButtonAction(ActionEvent event) {
        System.out.println("Edit");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kck/EditAnnouncmentWindow.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root, sceneWidth, sceneHeight);
            stage.setScene(scene);

            EditAnnouncmentController controller = loader.getController();
            controller.initialize(announcment);
            stage.show();
        });
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
