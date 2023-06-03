package com.example.kck;

public class ContactsFilterSettings {
    private String generalTextField;
    private String nameTextField;
    private String surnameTextField;
    private String emailTextField;
    private String townTextField;
    private String phoneNumberTextField;
    private String streetTextField;
    private String homeNumberTextField;
    private String descriptionTextField;

    public ContactsFilterSettings() {
        this.generalTextField = "";
        this.nameTextField = "";
        this.surnameTextField = "";
        this.emailTextField = "";
        this.townTextField = "";
        this.phoneNumberTextField = "";
        this.streetTextField = "";
        this.homeNumberTextField = "";
        this.descriptionTextField = "";
    }

    public String getGeneralTextField() {
        return generalTextField;
    }

    public void setGeneralTextField(String generalTextField) {
        this.generalTextField = generalTextField;
    }

    public String getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(String nameTextField) {
        this.nameTextField = nameTextField;
    }

    public String getSurnameTextField() {
        return surnameTextField;
    }

    public void setSurnameTextField(String surnameTextField) {
        this.surnameTextField = surnameTextField;
    }

    public String getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(String emailTextField) {
        this.emailTextField = emailTextField;
    }

    public String getTownTextField() {
        return townTextField;
    }

    public void setTownTextField(String townTextField) {
        this.townTextField = townTextField;
    }

    public String getPhoneNumberTextField() {
        return phoneNumberTextField;
    }

    public void setPhoneNumberTextField(String phoneNumberTextField) {
        this.phoneNumberTextField = phoneNumberTextField;
    }

    public String getStreetTextField() {
        return streetTextField;
    }

    public void setStreetTextField(String streetTextField) {
        this.streetTextField = streetTextField;
    }

    public String getHomeNumberTextField() {
        return homeNumberTextField;
    }

    public void setHomeNumberTextField(String homeNumberTextField) {
        this.homeNumberTextField = homeNumberTextField;
    }

    public String getDescriptionTextField() {
        return descriptionTextField;
    }

    public void setDescriptionTextField(String descriptionTextField) {
        this.descriptionTextField = descriptionTextField;
    }

    public void clear(){
        generalTextField = "";
        nameTextField = "";
        surnameTextField = "";
        emailTextField = "";
        townTextField = "";
        phoneNumberTextField = "";
        streetTextField = "";
        homeNumberTextField = "";
        descriptionTextField = "";
    }
}
