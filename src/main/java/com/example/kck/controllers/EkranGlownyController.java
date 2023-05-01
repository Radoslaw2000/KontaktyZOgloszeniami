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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
    @FXML
    TextField generalTextField, nameTextField, surnameTextField, emailTextField, townTextField, phoneNumberTextField, streetTextField, homeNumberTextField, descriptionTextField;

    @FXML
    MenuButton numberOfPagesMenu;

    public void dodajKontaktButtonAction(MouseEvent event) throws IOException {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("DodajKontaktWindow.fxml");
    }

    public void ogloszeniaButtonAction(MouseEvent event) {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene("MainWindow2.fxml");

    }

    public void filtrButtonAction(ActionEvent event) {
        System.out.println("filtry");
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
        showList();
    }

    public void gearButtonAction(MouseEvent event) {
        new GearOptions(event, gear);
    }

    private List<Kontakt> filtrowaneUlubione(List<Kontakt>  kontakty){
        List<Kontakt> filteredContacts = new ArrayList<>();

        for(Kontakt contact : kontakty) {
            boolean match = true;

            if(!generalTextField.getText().isEmpty()) {
                String generalLowerCase = generalTextField.getText().toLowerCase();
                if(!contact.getImie().toLowerCase().contains(generalLowerCase) &&
                        !contact.getNazwisko().toLowerCase().contains(generalLowerCase) &&
                        !contact.getNrTelefonu().toLowerCase().contains(generalLowerCase) &&
                        !contact.getEmail().toLowerCase().contains(generalLowerCase) &&
                        !contact.getMiejscowosc().toLowerCase().contains(generalLowerCase) &&
                        !contact.getUlica().toLowerCase().contains(generalLowerCase) &&
                        !contact.getNrDomu().toLowerCase().contains(generalLowerCase) &&
                        !contact.getOpis().toLowerCase().contains(generalLowerCase)) {
                    match = false;
                }
            }
            if(!nameTextField.getText().isEmpty() && !contact.getImie().toLowerCase().contains(nameTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!surnameTextField.getText().isEmpty() && !contact.getNazwisko().toLowerCase().contains(surnameTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!emailTextField.getText().isEmpty() && !contact.getEmail().toLowerCase().contains(emailTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!townTextField.getText().isEmpty() && !contact.getMiejscowosc().toLowerCase().contains(townTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!phoneNumberTextField.getText().isEmpty() && !contact.getNrTelefonu().toLowerCase().contains(phoneNumberTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!streetTextField.getText().isEmpty() && !contact.getUlica().toLowerCase().contains(streetTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!homeNumberTextField.getText().isEmpty() && !contact.getNrDomu().toLowerCase().contains(homeNumberTextField.getText().toLowerCase())) {
                match = false;
            }
            if(!descriptionTextField.getText().isEmpty() && !contact.getOpis().toLowerCase().contains(descriptionTextField.getText().toLowerCase())) {
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
        if(Settings.getInstance().isFavourite())
            Settings.getInstance().setFavourite(false);
        else
            Settings.getInstance().setFavourite(true);
        showList();
    }

    private void loadContacts(){
        DBMenager dbMenager = new DBMenager();
        //List<Kontakt> kontakty = dbMenager.selectContacts(Settings.getInstance().getPageNumber(), Settings.getInstance().getContactsNumberOnPage());
        List<Kontakt> kontakty = dbMenager.selectContactsFiltered(Settings.getInstance().getPageNumber(), Settings.getInstance().getContactsNumberOnPage(),
                generalTextField.getText(), nameTextField.getText(), surnameTextField.getText(), emailTextField.getText(), townTextField.getText(),
                phoneNumberTextField.getText(), streetTextField.getText(), homeNumberTextField.getText(), descriptionTextField.getText());
        for(int i = 0; i < kontakty.size(); i++){
            Kontakt kontakt = kontakty.get(i);
            KontaktGridPane kontaktGridPane = new KontaktGridPane(kontakt);
            content.getChildren().add(kontaktGridPane);
        }
    }
    private void loadFavouriteContacts(){
        DBMenager dbMenager = new DBMenager();
        List<Kontakt> contacts = dbMenager.selectFavoriteContacts();
        List<Kontakt> kontakty = filtrowaneUlubione(contacts);
        for(int i = 0; i < kontakty.size(); i++){
            Kontakt kontakt = kontakty.get(i);
            KontaktGridPane kontaktGridPane = new KontaktGridPane(kontakt);
            content.getChildren().add(kontaktGridPane);
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

    // metoda, która ustawia nową wartość w MenuButtonie i wyświetla ją w konsoli
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
                showList();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}