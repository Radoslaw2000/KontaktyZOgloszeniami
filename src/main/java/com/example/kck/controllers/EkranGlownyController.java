package com.example.kck.controllers;

import com.example.kck.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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




    public void dodajKontaktButtonAction(MouseEvent event) throws IOException {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("DodajKontaktWindow.fxml");
    }

    public void ogloszeniaButtonAction(MouseEvent event) {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("MainWindow2.fxml");

    }

    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event, gear);
    }

    public void ulubioneButtonAction(MouseEvent event){
        if(Settings.getInstance().isFavourite())
            Settings.getInstance().setFavourite(false);
        else
            Settings.getInstance().setFavourite(true);
        showList();
    }

    private void loadContacts(){
        DBMenager dbMenager = new DBMenager();
        List<Kontakt> kontakty = dbMenager.selectContacts(Settings.getInstance().getPageNumber(), Settings.getInstance().getContactsNumberOnPage());
        for(int i = 0; i < kontakty.size(); i++){
            Kontakt kontakt = kontakty.get(i);
            KontaktGridPane kontaktGridPane = new KontaktGridPane(kontakt);
            content.getChildren().add(kontaktGridPane);
        }
    }
    private void loadFavouriteContacts(){
        DBMenager dbMenager = new DBMenager();
        List<Kontakt> kontakty = dbMenager.selectFavoriteContacts();
        for(int i = 0; i < kontakty.size(); i++){
            Kontakt kontakt = kontakty.get(i);
            KontaktGridPane kontaktGridPane = new KontaktGridPane(kontakt);
            content.getChildren().add(kontaktGridPane);
        }
    }

    private void showList(){
        PageSelector ps = new PageSelector();
        PageSelector ps2 = new PageSelector();
        KontaktGridPane header = new KontaktGridPane();
        content.getChildren().clear();
        content.getChildren().add(ps);
        content.getChildren().add(header);

        if(Settings.getInstance().isFavourite())
            loadFavouriteContacts();
        else
            loadContacts();

        content.getChildren().add(ps2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(()->{
            try {
                content.setPadding(new Insets(0,10,0,10));
                userPanel.add(new LetterCircle(Settings.getInstance().getUser().getLogin().charAt(0),17), 0, 0);
                login.setText(Settings.getInstance().getUser().getLogin());
                content.getChildren().clear();
                showList();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}