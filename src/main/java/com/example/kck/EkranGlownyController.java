package com.example.kck;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EkranGlownyController implements Initializable {
    @FXML
    VBox content;

    @FXML
    ImageView gear;


    private void logout(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.setMaximized(false);
        stage.show();
    }
    public void dodajKontaktButtonAction(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajKontaktWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setScene(scene);
        stage.show();
    }

    public void ogloszeniaButtonAction(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow2.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setScene(scene);
        stage.show();
    }

    public void gearButtonAction(MouseEvent event) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem importItem = new MenuItem("Importuj kontakty...");
        MenuItem exportItem = new MenuItem("Exportuj kontakty...");
        MenuItem manageItem = new MenuItem("Zarządzaj folderami...");
        MenuItem logOutItem = new MenuItem("Wyloguj");

        logOutItem.setOnAction(e -> {
            logout((Stage) ((Node) event.getSource()).getScene().getWindow());
        });

        contextMenu.getItems().addAll(importItem, exportItem, manageItem, logOutItem);
        contextMenu.show(gear, event.getScreenX(), event.getScreenY());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            KontaktGridPane kontaktGridPane = new KontaktGridPane();
            for(int i = 0; i<10; i++){
                content.getChildren().add(kontaktGridPane);
                kontaktGridPane = new KontaktGridPane();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}