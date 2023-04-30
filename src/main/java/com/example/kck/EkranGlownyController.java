package com.example.kck;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EkranGlownyController implements Initializable {
    @FXML
    VBox content;

    @FXML
    GridPane userPanel;
    @FXML
    ImageView gear;
    @FXML
    Text login;



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

        Platform.runLater(()->{
            try {
                content.setPadding(new Insets(0,10,0,10));
                userPanel.add(new LetterCircle(Settings.getInstance().getUser().getLogin().charAt(0),17), 0, 0);
                login.setText(Settings.getInstance().getUser().getLogin());

                PageSelector ps = new PageSelector();
                PageSelector ps2 = new PageSelector();
                KontaktGridPane header = new KontaktGridPane();
                content.getChildren().add(ps);
                content.getChildren().add(header);

                DBMenager dbMenager = new DBMenager();
                List<Kontakt> kontakty = dbMenager.selectContacts(Settings.getInstance().getPageNumber(), Settings.getInstance().getContactsNumberOnPage());
                for(int i = 0; i < kontakty.size(); i++){
                    Kontakt kontakt = kontakty.get(i);
                    KontaktGridPane kontaktGridPane = new KontaktGridPane(kontakt);
                    content.getChildren().add(kontaktGridPane);
                }
                content.getChildren().add(ps2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}