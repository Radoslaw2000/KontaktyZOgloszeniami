package com.example.kck;

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
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
    Text login, nameText, surnameText, emailText, townText, phoneNumberText, streetText, homeNumberText;

    @FXML
    TextArea descriptionTextArea;

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

    public void editButtonAction(ActionEvent event) throws IOException {
        System.out.println("Edit");
    }

    public void deleteButtonAction(ActionEvent event) throws IOException {
        System.out.println("Delete");
    }

    public void likeButtonAction(ActionEvent event) throws IOException {
        System.out.println("Like");
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
    public void powrotButtonAction(ActionEvent event) throws IOException {
        // Pobranie sceny z przycisku
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
        double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

        // Wczytanie nowej sceny z pliku FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = loader.load();

        // Ustawienie nowej sceny
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setScene(scene);

        stage.show();
    }

    public void initialize(Kontakt kontakt) {
        Platform.runLater(() -> {
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

        });
    }
}
