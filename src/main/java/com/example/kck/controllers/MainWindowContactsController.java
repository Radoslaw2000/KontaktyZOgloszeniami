package com.example.kck.controllers;

import com.example.kck.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainWindowContactsController implements Initializable {
    @FXML
    VBox content;

    @FXML
    HBox favouriteHBox;

    @FXML
    GridPane userPanel;
    @FXML
    ImageView gear;
    @FXML
    Text login;
    @FXML
    TextField generalTextField, nameTextField, surnameTextField, emailTextField, townTextField, phoneNumberTextField, streetTextField, homeNumberTextField, descriptionTextField;

    @FXML
    MenuButton numberOfPagesMenu;

    private boolean isLastPage;

    public void homeButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
        Settings.getInstance().setFavourite(false);
        Settings.getInstance().switchScene("MainWindowContacts.fxml");
    }

    public void dodajKontaktButtonAction(MouseEvent event) throws IOException {
        Settings.getInstance().switchScene("AddContactWindow.fxml");
    }

    public void ogloszeniaButtonAction(MouseEvent event) {
        Settings.getInstance().switchScene("MainWindowAnnouncments.fxml");

    }

    public void filtrButtonAction(ActionEvent event) {
        //System.out.println("filtry");
        Settings.getInstance().getContactsFilterSettings().setGeneralTextField(generalTextField.getText());
        Settings.getInstance().getContactsFilterSettings().setNameTextField(nameTextField.getText());
        Settings.getInstance().getContactsFilterSettings().setSurnameTextField(surnameTextField.getText());
        Settings.getInstance().getContactsFilterSettings().setEmailTextField(emailTextField.getText());
        Settings.getInstance().getContactsFilterSettings().setTownTextField(townTextField.getText());
        Settings.getInstance().getContactsFilterSettings().setPhoneNumberTextField(phoneNumberTextField.getText());
        Settings.getInstance().getContactsFilterSettings().setStreetTextField(streetTextField.getText());
        Settings.getInstance().getContactsFilterSettings().setHomeNumberTextField(homeNumberTextField.getText());
        Settings.getInstance().getContactsFilterSettings().setDescriptionTextField(descriptionTextField.getText());

        showList();
    }

    public void clearButtonAction(ActionEvent event) {
        generalTextField.setText("");
        nameTextField.setText("");
        surnameTextField.setText("");
        phoneNumberTextField.setText("");
        emailTextField.setText("");
        townTextField.setText("");
        streetTextField.setText("");
        homeNumberTextField.setText("");
        descriptionTextField.setText("");
        Settings.getInstance().getContactsFilterSettings().clear();
        showList();
    }

    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event, gear);
    }

    private List<Contact> favouriteFiltered(List<Contact>  kontakty){
        List<Contact> filteredContacts = new ArrayList<>();

        for(Contact contact : kontakty) {
            boolean match = true;

            if(!generalTextField.getText().isEmpty()) {
                String generalLowerCase = generalTextField.getText().toLowerCase();
                if(!contact.getName().toLowerCase().contains(generalLowerCase) &&
                        !contact.getSurname().toLowerCase().contains(generalLowerCase) &&
                        !contact.getPhoneNumber().toLowerCase().contains(generalLowerCase) &&
                        !contact.getEmail().toLowerCase().contains(generalLowerCase) &&
                        !contact.getTown().toLowerCase().contains(generalLowerCase) &&
                        !contact.getStreet().toLowerCase().contains(generalLowerCase) &&
                        !contact.getHouseNumber().toLowerCase().contains(generalLowerCase) &&
                        !contact.getDescription().toLowerCase().contains(generalLowerCase)) {
                    match = false;
                }
            }
            if(!nameTextField.getText().isEmpty() && !contact.getName().toLowerCase().contains(nameTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!surnameTextField.getText().isEmpty() && !contact.getSurname().toLowerCase().contains(surnameTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!emailTextField.getText().isEmpty() && !contact.getEmail().toLowerCase().contains(emailTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!townTextField.getText().isEmpty() && !contact.getTown().toLowerCase().contains(townTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!phoneNumberTextField.getText().isEmpty() && !contact.getPhoneNumber().toLowerCase().contains(phoneNumberTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!streetTextField.getText().isEmpty() && !contact.getStreet().toLowerCase().contains(streetTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!homeNumberTextField.getText().isEmpty() && !contact.getHouseNumber().toLowerCase().contains(homeNumberTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!descriptionTextField.getText().isEmpty() && !contact.getDescription().toLowerCase().contains(descriptionTextField.getText().toLowerCase())) {
                match = false;
            }
            if(match) {
                filteredContacts.add(contact);
            }
        }

        return filteredContacts;


    }

    public void ulubioneButtonAction(MouseEvent event){
        Settings.getInstance().setPageNumber(1);
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

        showList();
    }

    private void loadContacts(){
        DBMenager dbMenager = new DBMenager();
        //List<Kontakt> kontakty = dbMenager.selectContacts(Settings.getInstance().getPageNumber(), Settings.getInstance().getContactsNumberOnPage());
        List<Contact> kontakty = dbMenager.selectContactsFiltered(Settings.getInstance().getPageNumber(), Settings.getInstance().getContactsNumberOnPage(),
                generalTextField.getText(), nameTextField.getText(), surnameTextField.getText(), emailTextField.getText(), townTextField.getText(),
                phoneNumberTextField.getText(), streetTextField.getText(), homeNumberTextField.getText(), descriptionTextField.getText());

        isLastPage = kontakty.size() != Settings.getInstance().getContactsNumberOnPage();

        for(int i = 0; i < kontakty.size(); i++){
            Contact contact = kontakty.get(i);
            ContaktGridPane contaktGridPane = new ContaktGridPane(contact);
            content.getChildren().add(contaktGridPane);
        }
    }
    private void loadFavouriteContacts(){
        DBMenager dbMenager = new DBMenager();
        List<Contact> contacts = dbMenager.selectFavoriteContacts();
        List<Contact> kontakty = favouriteFiltered(contacts);

        isLastPage = kontakty.size() != Settings.getInstance().getContactsNumberOnPage();

        for(int i = 0; i < kontakty.size(); i++){
            Contact contact = kontakty.get(i);
            ContaktGridPane contaktGridPane = new ContaktGridPane(contact);
            content.getChildren().add(contaktGridPane);
        }
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

    private void showList(){
        PageSelector ps = new PageSelector();
        PageSelector ps2 = new PageSelector();
        setEventsToPageSelector(ps);
        setEventsToPageSelector(ps2);

        ContaktGridPane header = new ContaktGridPane();
        content.getChildren().clear();
        content.getChildren().add(ps);
        content.getChildren().add(header);

        if(Settings.getInstance().isFavourite()){
            favouriteHBox.getStyleClass().clear();
            favouriteHBox.getStyleClass().add("dodaj-contact-button2");
            loadFavouriteContacts();
        }
        else
            loadContacts();

        content.getChildren().add(ps2);

        ps.hideRightArrow(!isLastPage);
        ps2.hideRightArrow(!isLastPage);
    }


    private void setNewValue(String newValue) {
        Settings.getInstance().setContactsNumberOnPage(Integer.parseInt(newValue));
        numberOfPagesMenu.setText(newValue);
        showList();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(()->{
            try {
                numberOfPagesMenu.setText(String.valueOf(Settings.getInstance().getContactsNumberOnPage()));
                for (MenuItem item : numberOfPagesMenu.getItems()) {
                    item.setOnAction(event -> setNewValue(item.getText()));
                }
                content.setPadding(new Insets(0,10,0,10));
                userPanel.add(new LetterCircle(Settings.getInstance().getUser().getLogin().charAt(0),17), 0, 0);
                login.setText(Settings.getInstance().getUser().getLogin());
                content.getChildren().clear();

                generalTextField.setText(Settings.getInstance().getContactsFilterSettings().getGeneralTextField());
                nameTextField.setText(Settings.getInstance().getContactsFilterSettings().getNameTextField());
                surnameTextField.setText(Settings.getInstance().getContactsFilterSettings().getSurnameTextField());
                emailTextField.setText(Settings.getInstance().getContactsFilterSettings().getEmailTextField());
                townTextField.setText(Settings.getInstance().getContactsFilterSettings().getTownTextField());
                phoneNumberTextField.setText(Settings.getInstance().getContactsFilterSettings().getPhoneNumberTextField());
                streetTextField.setText(Settings.getInstance().getContactsFilterSettings().getStreetTextField());
                homeNumberTextField.setText(Settings.getInstance().getContactsFilterSettings().getHomeNumberTextField());
                descriptionTextField.setText(Settings.getInstance().getContactsFilterSettings().getDescriptionTextField());

                showList();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}