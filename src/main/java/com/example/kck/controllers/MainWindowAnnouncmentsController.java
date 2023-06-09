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
import java.util.ArrayList;
import java.util.List;
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

    private boolean isLastPage;

    public void homeButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().setFavourite(false);
        Settings.getInstance().setCategory(null);
        Settings.getInstance().getAnnouncmentsFilterSettings().clear();
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


    private List<Announcment> favouriteFiltered(List<Announcment> announcments) {
        List<Announcment> filteredList = new ArrayList<>();

        for (Announcment announcment : announcments) {
            boolean match = true;

            if (!generalTextField.getText().isEmpty()) {
                String generalLowerCase = generalTextField.getText().toLowerCase();
                if (!announcment.getTitle().toLowerCase().contains(generalLowerCase) &&
                        !announcment.getDescription().toLowerCase().contains(generalLowerCase) &&
                        !announcment.getTown().toLowerCase().contains(generalLowerCase) &&
                        !announcment.getCategory().toLowerCase().contains(generalLowerCase) &&
                        !announcment.getPhoneNumber().toLowerCase().contains(generalLowerCase) &&
                        !announcment.getVoivodeship().toLowerCase().contains(generalLowerCase)) {
                    match = false;
                }
            }

            if (!titleTextField.getText().isEmpty() && !announcment.getTitle().toLowerCase().contains(titleTextField.getText().toLowerCase())) {
                match = false;
            }
            if (!descriptionTextField.getText().isEmpty() && !announcment.getDescription().toLowerCase().contains(descriptionTextField.getText().toLowerCase())) {
                match = false;
            }
            if (!townTextField.getText().isEmpty() && !announcment.getTown().toLowerCase().contains(townTextField.getText().toLowerCase())) {
                match = false;
            }


            if (match) {
                filteredList.add(announcment);
            }
        }

        return filteredList;
    }

    private void loadFavouriteAnnouncments(){
        DBMenager dbMenager = new DBMenager();
        List<Announcment> announcments = dbMenager.selectFavoriteAnnouncments();
        List<Announcment> ogloszenia = favouriteFiltered(announcments);
        isLastPage = ogloszenia.size() != Settings.getInstance().getContactsNumberOnPage();

        for(int i = 0; i < ogloszenia.size(); i++){
            Announcment announcment = ogloszenia.get(i);
            AnnouncmentGridPane announcmentGridPane = new AnnouncmentGridPane(announcment);
            content.getChildren().add(announcmentGridPane);
        }
    }


    private void loadAnnouncments(){
        String generalText, title, description, priceFrom, priceTo, town, voivodeship;
        generalText = generalTextField.getText();
        title = titleTextField.getText();
        description = descriptionTextField.getText();
        priceFrom = priceFromTextField.getText();
        priceTo = priceToTextField.getText();
        town = townTextField.getText();
        voivodeship = (isNull(voivodeshipComboBox.getSelectionModel().getSelectedItem())) ? "cała polska" : voivodeshipComboBox.getSelectionModel().getSelectedItem();

        DBMenager dbMenager = new DBMenager();
        List<Announcment> announcments = dbMenager.selectAnnouncmentFiltered(Settings.getInstance().getPageNumber(), Settings.getInstance().getContactsNumberOnPage(),
                generalText, title, description, priceFrom, priceTo, town, voivodeship);

        isLastPage = announcments.size() != Settings.getInstance().getContactsNumberOnPage();

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

        if(Settings.getInstance().isFavourite()){
            favouriteHBox.getStyleClass().clear();
            favouriteHBox.getStyleClass().add("dodaj-contact-button2");
            loadFavouriteAnnouncments();
        }
        else
        {
            //Settings.getInstance().setCategory("Wszystko");
            loadAnnouncments();
        }
        content.getChildren().add(ps2);
        ps.hideRightArrow(!isLastPage);
        ps2.hideRightArrow(!isLastPage);
    }

    public void ulubioneButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().setCategory("Wszystko");
        if(Settings.getInstance().isFavourite()){
            Settings.getInstance().setFavourite(false);
            favouriteHBox.getStyleClass().clear();
            favouriteHBox.getStyleClass().add("dodaj-contact-button");
        }
        else{
            Settings.getInstance().setFavourite(true);
            favouriteHBox.getStyleClass().clear();
            favouriteHBox.getStyleClass().add("dodaj-contact-button2");
        }

        System.out.println(Settings.getInstance().getCategory());
        showList();
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
        if(!generalTextField.getText().isEmpty())
            Settings.getInstance().getAnnouncmentsFilterSettings().setGeneralTextField(generalTextField.getText());
        System.out.println(Settings.getInstance().getAnnouncmentsFilterSettings().getGeneralTextField());

        Settings.getInstance().getAnnouncmentsFilterSettings().setTitleTextField(titleTextField.getText());
        Settings.getInstance().getAnnouncmentsFilterSettings().setDescriptionTextField(descriptionTextField.getText());
        Settings.getInstance().getAnnouncmentsFilterSettings().setPriceFromTextField(priceFromTextField.getText());
        Settings.getInstance().getAnnouncmentsFilterSettings().setPriceToTextField(priceToTextField.getText());
        Settings.getInstance().getAnnouncmentsFilterSettings().setTownTextField(townTextField.getText());
        Settings.getInstance().getAnnouncmentsFilterSettings().setVoivodeshipComboBox((isNull(voivodeshipComboBox.getSelectionModel().getSelectedItem())) ? "" : voivodeshipComboBox.getSelectionModel().getSelectedItem());

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
        voivodeshipComboBox.getSelectionModel().select("cała polska");
        Settings.getInstance().getAnnouncmentsFilterSettings().clear();
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

                isLastPage = false;
                numberOfPagesMenu.setText(String.valueOf(Settings.getInstance().getContactsNumberOnPage()));
                for (MenuItem item : numberOfPagesMenu.getItems()) {
                    item.setOnAction(event -> setNewValue(item.getText()));
                }
                ObservableList<String> voievodeships = voivodeshipComboBox.getItems();
                voievodeships.addAll("cała polska", "dolnośląskie", "kujawsko-pomorskie", "lubelskie", "lubuskie", "łódzkie", "małopolskie", "mazowieckie", "opolskie", "podkarpackie", "podlaskie", "pomorskie", "śląskie", "świętokrzyskie", "warmińsko-mazurskie", "wielkopolskie", "zachodniopomorskie");
                voivodeshipComboBox.setItems(voievodeships);
                Settings.getInstance().getAnnouncmentsFilterSettings().setVoivodeshipComboBox((isNull(voivodeshipComboBox.getSelectionModel().getSelectedItem())) ? "cała polska" : voivodeshipComboBox.getSelectionModel().getSelectedItem());

                generalTextField.setText(Settings.getInstance().getAnnouncmentsFilterSettings().getGeneralTextField());
                titleTextField.setText(Settings.getInstance().getAnnouncmentsFilterSettings().getTitleTextField());
                descriptionTextField.setText(Settings.getInstance().getAnnouncmentsFilterSettings().getDescriptionTextField());
                priceFromTextField.setText(Settings.getInstance().getAnnouncmentsFilterSettings().getPriceFromTextField());
                priceToTextField.setText(Settings.getInstance().getAnnouncmentsFilterSettings().getPriceToTextField());
                townTextField.setText(Settings.getInstance().getAnnouncmentsFilterSettings().getTownTextField());
                voivodeshipComboBox.getSelectionModel().select(Settings.getInstance().getAnnouncmentsFilterSettings().getVoivodeshipComboBox());

                if(isNull(Settings.getInstance().getCategory()))
                    responsiveCategoryMenu(Settings.getInstance().getStage().getScene());
                else
                    showList();

                userPanel.add(new LetterCircle(Settings.getInstance().getUser().getLogin().charAt(0),17), 0, 0);
                login.setText(Settings.getInstance().getUser().getLogin());

            } catch (Exception e) {
                e.printStackTrace();
            }

        });


    }
}