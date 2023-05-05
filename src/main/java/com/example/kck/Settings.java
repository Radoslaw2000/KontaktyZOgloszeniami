package com.example.kck;

import javafx.stage.Stage;

public class Settings {

    private static Settings instance;
    private Stage stage;

    private SceneSwitcher sceneSwitcher;
    private User user;
    private int pageNumber;
    private int contactsNumberOnPage;
    private boolean isFavourite;

    private Settings() {
        pageNumber = 1;
        contactsNumberOnPage = 20;
        sceneSwitcher = new SceneSwitcher();
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public void switchScene(String fxmlName){
        sceneSwitcher.switchScene(fxmlName);
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getContactsNumberOnPage() {
        return contactsNumberOnPage;
    }

    public void setContactsNumberOnPage(int contactsNumberOnPage) {
        this.contactsNumberOnPage = contactsNumberOnPage;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }
}
