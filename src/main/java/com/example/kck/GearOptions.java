package com.example.kck;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;



public class GearOptions {

    public GearOptions(MouseEvent event, ImageView gear){
        ContextMenu contextMenu = new ContextMenu();
        MenuItem helpItem = new MenuItem("Pomoc...");
        MenuItem logOutItem = new MenuItem("Wyloguj");

        logOutItem.setOnAction(e -> logout());

        helpItem.setOnAction(e -> {
            BrowserOpener.openHTMLFile("pomoc.html");
        });

        contextMenu.getItems().addAll(helpItem, logOutItem);
        contextMenu.show(gear, event.getScreenX(), event.getScreenY());
    }


    private void logout() {
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().switchScene("StartWindow.fxml");
    }


}
