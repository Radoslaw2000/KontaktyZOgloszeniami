package com.example.kck.controllers;

import com.example.kck.GearOptions;
import com.example.kck.SceneSwitcher;
import com.example.kck.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ShowAnnouncmentDetailsController {

    @FXML
    ImageView gear;

    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event, gear);
    }

    public void powrotButtonAction(ActionEvent event) throws IOException {
        Settings.getInstance().switchScene("MainWindowAnnouncments.fxml");

    }


}
