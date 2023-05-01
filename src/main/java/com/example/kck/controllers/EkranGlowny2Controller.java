package com.example.kck.controllers;


import com.example.kck.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class EkranGlowny2Controller implements Initializable {
    @FXML
    VBox content;

    @FXML
    ImageView gear;

    @FXML
    VBox katalogi;

    public void dodajOgloszenieButtonAction(MouseEvent event) {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("DodajOgloszenieWindow.fxml");
    }

    public void kontaktyButtonAction(MouseEvent event) {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("MainWindow.fxml");
    }

    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event,gear);
    }

    private void responsiveCategoryMenu(Scene scene) {
        double sceneWidth = scene.getWidth() - 200.0;
        int columns1 = (int) (sceneWidth / 130.0);
        content.getChildren().add(0, new CategoryGridPane(columns1, content));

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
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
                katalogi.getChildren().add(new Filtry());
            /*
            OgloszenieGridPane ogloszenieGridPane;
            for(int i = 0; i<10; i++) {
                ogloszenieGridPane = new OgloszenieGridPane();
                content.getChildren().add(ogloszenieGridPane);
            }
             */
            } catch (Exception e) {
                e.printStackTrace();
            }

        });


    }
}