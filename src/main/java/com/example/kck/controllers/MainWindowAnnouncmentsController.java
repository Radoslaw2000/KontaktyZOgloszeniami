package com.example.kck.controllers;

import com.example.kck.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.util.Objects.isNull;

public class MainWindowAnnouncmentsController implements Initializable {
    @FXML
    VBox content, katalogi;
    @FXML
    HBox favouriteHBox;
    @FXML
    GridPane userPanel;
    @FXML
    ImageView gear;

    @FXML
    Text login;

    @FXML
    TextField generalTextField, titleTextField, descriptionTextField, priceFromTextField, priceToTextField, townTextField;

    @FXML
    ComboBox<String> voivodeshipComboBox;

    @FXML
    MenuButton numberOfPagesMenu;


    public void homeButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().setFavourite(false);
        Settings.getInstance().setCategory(null);
        Settings.getInstance().switchScene("MainWindowAnnouncments.fxml");
    }
    private void setEventsToPageSelector(PageSelector ps){
        ps.getIncrese().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ps.increse();
                showList();
            }
        });
        ps.getDecrese().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ps.decrese();
                showList();
            }
        });
    }

    private void loadContacts(){
        String generalText, title, description, priceFrom, priceTo, town, voivodeship;
        generalText = generalTextField.getText();
        title = titleTextField.getText();
        description = descriptionTextField.getText();
        priceFrom = priceFromTextField.getText();
        priceTo = priceToTextField.getText();
        town = townTextField.getText();
        voivodeship = (isNull(voivodeshipComboBox.getSelectionModel().getSelectedItem())) ? "" : voivodeshipComboBox.getSelectionModel().getSelectedItem();

        DBMenager dbMenager = new DBMenager();
        List<Announcment> announcments = dbMenager.selectAnnouncmentFiltered(Settings.getInstance().getPageNumber(), Settings.getInstance().getContactsNumberOnPage(),
                generalText, title, description, priceFrom, priceTo, town, voivodeship);

        for (Announcment announcment : announcments) {
            content.getChildren().add(new AnnouncmentGridPane(announcment));
        }
    }
    private void showList(){
        PageSelector ps = new PageSelector();
        PageSelector ps2 = new PageSelector();
        setEventsToPageSelector(ps);
        setEventsToPageSelector(ps2);

        content.getChildren().clear();
        content.getChildren().add(ps);
/*
        if(Settings.getInstance().isFavourite()){
            favouriteHBox.getStyleClass().clear();
            favouriteHBox.getStyleClass().add("dodaj-contact-button2");
            //loadFavouriteContacts();
        }
        else */
            loadContacts();

        content.getChildren().add(ps2);
    }

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
        if(isNull(Settings.getInstance().getCategory()))
            Settings.getInstance().setCategory("Wszystko");
        showList();
    }

    public void clearButtonAction(ActionEvent event) {
        System.out.println("clear");
        generalTextField.setText("");
        titleTextField.setText("");
        descriptionTextField.setText("");
        priceFromTextField.setText("");
        priceToTextField.setText("");
        townTextField.setText("");
        voivodeshipComboBox.getSelectionModel().clearSelection();

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

    private void setNewValue(String newValue) {
        Settings.getInstance().setContactsNumberOnPage(Integer.parseInt(newValue));
        numberOfPagesMenu.setText(newValue);
        if(!isNull(Settings.getInstance().getCategory()))
            showList();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {
            try {
                numberOfPagesMenu.setText(String.valueOf(Settings.getInstance().getContactsNumberOnPage()));
                for (MenuItem item : numberOfPagesMenu.getItems()) {
                    item.setOnAction(event -> setNewValue(item.getText()));
                }
                ObservableList<String> voievodeships = voivodeshipComboBox.getItems();
                voievodeships.addAll("cała polska", "dolnośląskie", "kujawsko-pomorskie", "lubelskie", "lubuskie", "łódzkie", "małopolskie", "mazowieckie", "opolskie", "podkarpackie", "podlaskie", "pomorskie", "śląskie", "świętokrzyskie", "warmińsko-mazurskie", "wielkopolskie", "zachodniopomorskie");
                voivodeshipComboBox.setItems(voievodeships);

                if(isNull(Settings.getInstance().getCategory()))
                    responsiveCategoryMenu(Settings.getInstance().getStage().getScene());

                userPanel.add(new LetterCircle(Settings.getInstance().getUser().getLogin().charAt(0),17), 0, 0);
                login.setText(Settings.getInstance().getUser().getLogin());

            } catch (Exception e) {
                e.printStackTrace();
            }

        });


    }
}