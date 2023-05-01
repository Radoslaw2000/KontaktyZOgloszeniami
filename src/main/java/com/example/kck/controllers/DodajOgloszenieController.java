package com.example.kck.controllers;

import com.example.kck.GearOptions;
import com.example.kck.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class DodajOgloszenieController {

    @FXML
    ImageView gear;

    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event,gear);
    }
    public void anulujButtonAction(ActionEvent event) throws IOException {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("MainWindow2.fxml");
    }


}
