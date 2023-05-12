package com.example.kck.controllers;

import com.example.kck.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static java.util.Objects.isNull;

public class MainWindowAnnouncmentsController implements Initializable {
    @FXML
    VBox content, katalogi;
    @FXML
    GridPane userPanel;
    @FXML
    ImageView gear;

    @FXML
    Text login;


    public void ulubioneButtonAction(MouseEvent event){

    }
    public void dodajOgloszenieButtonAction(MouseEvent event) {
        Settings.getInstance().switchScene("AddAnnouncmentWindow.fxml");
    }

    public void kontaktyButtonAction(MouseEvent event) {
        Settings.getInstance().setCategory(null);
        Settings.getInstance().switchScene("MainWindowContacts.fxml");
    }

    public void filtrButtonAction(ActionEvent event) {
        System.out.println("filtry");
    }

    public void clearButtonAction(ActionEvent event) {
        System.out.println("clear");
    }

    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event,gear);
    }

    private void responsiveCategoryMenu(Scene scene) {
        double sceneWidth = scene.getWidth() - 200.0;
        int columns1 = (int) (sceneWidth / 130.0);
        content.getChildren().add(0, new CategoryGridPane(columns1, content));

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            if(!isNull(Settings.getInstance().getCategory())){
                return;
            }
            double newWidth = (double) newValue - 200.0;
            int columns = (int) (newWidth / 135.0);
            content.getChildren().set(0, new CategoryGridPane(columns, content));
        });
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {
            try {
                responsiveCategoryMenu(Settings.getInstance().getStage().getScene());
                userPanel.add(new LetterCircle(Settings.getInstance().getUser().getLogin().charAt(0),17), 0, 0);
                login.setText(Settings.getInstance().getUser().getLogin());

            } catch (Exception e) {
                e.printStackTrace();
            }

        });


    }
}