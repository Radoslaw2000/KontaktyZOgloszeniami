package com.example.kck;

public class AnnouncmentsFilterSettings {
    private String generalTextField;
    private String titleTextField;
    private String descriptionTextField;
    private String priceFromTextField;
    private String priceToTextField;
    private String townTextField;
    private String voivodeshipComboBox;

    public AnnouncmentsFilterSettings() {
        this.generalTextField = "";
        this.titleTextField = "";
        this.descriptionTextField = "";
        this.priceFromTextField = "";
        this.priceToTextField = "";
        this.townTextField = "";
        this.voivodeshipComboBox = "";
    }

    public String getGeneralTextField() {
        return generalTextField;
    }

    public void setGeneralTextField(String generalTextField) {
        this.generalTextField = generalTextField;
    }

    public String getTitleTextField() {
        return titleTextField;
    }

    public void setTitleTextField(String titleTextField) {
        this.titleTextField = titleTextField;
    }

    public String getDescriptionTextField() {
        return descriptionTextField;
    }

    public void setDescriptionTextField(String descriptionTextField) {
        this.descriptionTextField = descriptionTextField;
    }

    public String getPriceFromTextField() {
        return priceFromTextField;
    }

    public void setPriceFromTextField(String priceFromTextField) {
        this.priceFromTextField = priceFromTextField;
    }

    public String getPriceToTextField() {
        return priceToTextField;
    }

    public void setPriceToTextField(String priceToTextField) {
        this.priceToTextField = priceToTextField;
    }

    public String getTownTextField() {
        return townTextField;
    }

    public void setTownTextField(String townTextField) {
        this.townTextField = townTextField;
    }

    public String getVoivodeshipComboBox() {
        return voivodeshipComboBox;
    }

    public void setVoivodeshipComboBox(String voivodeshipComboBox) {
        this.voivodeshipComboBox = voivodeshipComboBox;
    }

    public void clear(){
        generalTextField = "";
        titleTextField = "";
        descriptionTextField = "";
        priceFromTextField = "";
        priceToTextField = "";
        townTextField = "";
        voivodeshipComboBox = "";
    }
}
