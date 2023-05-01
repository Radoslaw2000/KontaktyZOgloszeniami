package com.example.kck;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GearOptions {

    public GearOptions(MouseEvent event, ImageView gear){
        ContextMenu contextMenu = new ContextMenu();
        MenuItem importItem = new MenuItem("Importuj kontakty...");
        MenuItem exportItem = new MenuItem("Exportuj kontakty...");
        MenuItem logOutItem = new MenuItem("Wyloguj");

        logOutItem.setOnAction(e -> logout());

        contextMenu.getItems().addAll(importItem, exportItem, logOutItem);
        contextMenu.show(gear, event.getScreenX(), event.getScreenY());
    }


    private void logout() {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("StartWindow.fxml");
    }


}
